package theSorceress.components;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
//
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theSorceress.powers.Concentration;

public abstract class AbstractComponent extends AbstractOrb {
    private String invokeDescription;
    private String attuneDescription;
    public int baseConcentration;
    public State currentState;


    public AbstractComponent(String ID, String NAME/*, int basePassiveAmount, int baseEvokeAmount*/, String invokeDescription, String attuneDescription, String imgPath) {
        this.ID = ID;
        this.name = NAME;
        //this.basePassiveAmount = basePassiveAmount;
        //this.passiveAmount = this.basePassiveAmount;
        //this.baseEvokeAmount = baseEvokeAmount;
        //this.evokeAmount = this.baseEvokeAmount;
        this.invokeDescription = invokeDescription;
        this.attuneDescription = attuneDescription;
        this.currentState = State.DORMANT;
        this.img = ImageMaster.loadImage(imgPath);
        this.updateDescription();
        this.updateConcentration();
    }

    public void updateDescription() {
        this.applyFocus();
        this.description = "#yInvkoked: " + this.invokeDescription + " NL #yAttuned: " + this.attuneDescription + " NL #yState: " + this.currentState;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.c);
        sb.draw(this.img, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F + this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    public void updateConcentration()
    {
        this.baseConcentration = 0;
        for(AbstractPower p: AbstractDungeon.player.powers)
        {
            if(p instanceof Concentration)
            {
                baseConcentration = p.amount;
            }
        }
    }
    public void attune()
    {
        this.currentState = State.ATTUNED;
    }
    public void invoke()
    {
        this.currentState = State.INVOKED;
    }

    public enum State
    {
        INVOKED,
        ATTUNED,
        DORMANT
    }
    public abstract void onCast();
}
