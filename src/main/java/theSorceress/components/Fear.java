package theSorceress.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;
import theSorceress.SorceressMod;

import static theSorceress.SorceressMod.makeOrbPath;

public class Fear extends AbstractComponent {
    public static final String COMPONENT_ID = SorceressMod.makeID("Fear");
    private static final OrbStrings componentString = CardCrawlGame.languagePack.getOrbString(COMPONENT_ID);
    public static final String[] DESCRIPTIONS = componentString.DESCRIPTION;

    private float vfxTimer = 1.0f;
    private float vfxIntervalMin = 0.1f;
    private float vfxIntervalMax = 0.4f;
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;

    public Fear() {
        super(COMPONENT_ID, componentString.NAME, DESCRIPTIONS[0], DESCRIPTIONS[1], makeOrbPath("Fear.png"));
        updateDescription();

        angle = MathUtils.random(360.0f); // More Animation-related Numbers
        channelAnimTimer = 0.5f;
    }

    @Override
    public void onCast() {
        this.updateConcentration();
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        AbstractPlayer p = AbstractDungeon.player;
        if(this.currentState == State.INVOKED)
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new DexterityPower(m, this.baseConcentration * -1)));
        }
        if(this.currentState == State.ATTUNED)
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.baseConcentration, false)));
        }
    }

    @Override
    public void onEvoke() {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("TINGSHA"));
    }

    @Override
    public void playChannelSFX() { // When you channel this orb, the ATTACK_FIRE effect plays ("Fwoom").
        CardCrawlGame.sound.play("ATTACK_FIRE", 0.1f);
    }

    @Override
    public AbstractComponent makeCopy() {
        return new Fear();
    }

    @Override
    public void updateAnimation() {// You can totally leave this as is.
        // If you want to create a whole new orb effect - take a look at conspire's Water Orb. It includes a custom sound, too!
        super.updateAnimation();
        angle += Gdx.graphics.getDeltaTime() * 45.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f) {
            AbstractDungeon.effectList.add(new DarkOrbPassiveEffect(cX, cY)); // This is the purple-sparkles in the orb. You can change this to whatever fits your orb.
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }
    }

    // Render the orb.
    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, c.a / 2.0f));
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, scale, angle, 0, 0, 96, 96, false, false);
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.c.a / 2.0f));
        sb.setBlendFunction(770, 1);
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, -angle, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 771);
        hb.render(sb);
    }

    @Override
    public void updateDescription()
    {
        this.updateConcentration();
        if(this.currentState == State.INVOKED)
        {
            this.description = DESCRIPTIONS[0] + this.baseConcentration + DESCRIPTIONS[1];
        }
        if(this.currentState == State.ATTUNED)
        {
            this.description = DESCRIPTIONS[2] + this.baseConcentration + DESCRIPTIONS[3];
        }

    }
}
