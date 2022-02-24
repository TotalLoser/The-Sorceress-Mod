package theSorceress.actions;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import theSorceress.components.AbstractComponent;
import theSorceress.characters.TheSorceress;

public class InvokeAction extends AbstractGameAction {
    AbstractPlayer p;
    AbstractComponent c;

    public InvokeAction(AbstractPlayer p, AbstractComponent c)
    {
        this.p = p;
        this.c = c;

    }

    @Override
    public void update() {
        c.invoke();
        c.updateDescription();
        if(!(this.p instanceof TheSorceress))
        {
            this.isDone = true;
        }
        this.c.cX = p.orbs.get(0).cX;
        this.c.cY = p.orbs.get(0).cY;
        p.orbs.set(0, this.c);
        p.orbs.get(0).setSlot(0, p.maxOrbs);
        //((AbstractComponent) p.orbs.get(0)).invoke();
        c.playChannelSFX();
        this.isDone = true;
        //((AbstractComponent) p.orbs.get(0)).invoke();
    }
}
