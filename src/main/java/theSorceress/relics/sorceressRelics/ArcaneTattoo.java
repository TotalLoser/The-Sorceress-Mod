package theSorceress.relics.sorceressRelics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSorceress.SorceressMod;
import theSorceress.powers.Concentration;
import theSorceress.util.TextureLoader;

import static theSorceress.SorceressMod.makeRelicOutlinePath;
import static theSorceress.SorceressMod.makeRelicPath;

public class ArcaneTattoo extends CustomRelic{
    public static final String ID = SorceressMod.makeID("ArcaneTattoo");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public ArcaneTattoo() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atTurnStartPostDraw()//Gain 2 Concentration at turn start
    {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Concentration(p, p, 2)));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
