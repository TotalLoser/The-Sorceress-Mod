package theSorceress.relics.sorceressRelics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theSorceress.SorceressMod;
import theSorceress.powers.Concentration;
import theSorceress.util.TextureLoader;

import static theSorceress.SorceressMod.makeRelicOutlinePath;
import static theSorceress.SorceressMod.makeRelicPath;

public class ArcaneTattoo extends SorceressRelic{
    public static final String ID = SorceressMod.makeID("ArcaneTattoo");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public ArcaneTattoo() {

        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
        tips.clear();
        tips.add(new PowerTip(name, description));
        this.initializeTips();
    }

    @Override
    public void onCast() {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Concentration(p, p, 1)));
    }

//    @Override
//    public void atTurnStartPostDraw()//Gain 2 Concentration at turn start
//    {
//        flash();
//        AbstractPlayer p = AbstractDungeon.player;
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Concentration(p, p, 2)));
//    }

    // Description
//    public void setDescriptionAfterLoading() {
//        this.tips.clear();
//        this.tips.add(new PowerTip(this.name, this.description));
//
//    }
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
