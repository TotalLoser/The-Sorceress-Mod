package theSorceress.relics.sorceressRelics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theSorceress.SorceressMod;
import theSorceress.util.TextureLoader;

import static theSorceress.SorceressMod.makeRelicOutlinePath;
import static theSorceress.SorceressMod.makeRelicPath;

public abstract class SorceressRelic extends CustomRelic {


    public SorceressRelic(String id, Texture texture, Texture outline, RelicTier tier, LandingSound sfx) {
        super(id, texture, outline, tier, sfx);
    }

    public abstract void onCast();
}
