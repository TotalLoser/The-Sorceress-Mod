package theSorceress.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import theSorceress.characters.TheSorceress;
import theSorceress.components.AbstractComponent;

public class AttuneAction extends AbstractGameAction {
    AbstractPlayer p;
    AbstractComponent c;

    public AttuneAction(AbstractPlayer p, AbstractComponent c)
    {
        this.p = p;
        this.c = c;

    }

    @Override
    public void update() {
        c.attune();
        c.updateDescription();
        if(!(this.p instanceof TheSorceress))
        {
            this.isDone = true;
        }
        int index;
        for(index = 1; index < p.orbs.size(); index++)
        {
            if(p.orbs.get(index) instanceof EmptyOrbSlot)
                break;
        }
        if(index < p.orbs.size())
        {
            this.c.cX = p.orbs.get(index).cX;
            this.c.cY = p.orbs.get(index).cY;
            p.orbs.set(index, this.c);
            p.orbs.get(index).setSlot(index, p.maxOrbs);
            c.playChannelSFX();
        }
        else
        {
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, ((TheSorceress)p).getFullSlotsText(), true));
        }
        this.isDone = true;
    }
}
