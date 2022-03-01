package theSorceress.actions;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import theSorceress.characters.TheSorceress;
import theSorceress.components.AbstractComponent;
import theSorceress.powers.Concentration;
import theSorceress.relics.sorceressRelics.SorceressRelic;

public class CastAction extends AbstractGameAction {
    private AbstractPlayer p;
    public CastAction(final AbstractPlayer p)
    {
        this.p = p;
    }
    @Override
    public void update() {
        //trigger all relic cast triggers
        for(AbstractRelic relic : AbstractDungeon.player.relics)
        {
            if(relic instanceof SorceressRelic)
            {
                ((SorceressRelic) relic).onCast();
            }
        }
        //find concentration index
        int index = -1;
        for (int i = 0; i < p.powers.size(); i++)
        {
            if (p.powers.get(i) instanceof Concentration)
            {
                index = i;
            }
        }

        //if no concnetration
        if(index == -1) {
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, ((TheSorceress)p).getNoConcentrateText(), true));
            isDone = true;
        }
        else
        {
            //trigger on component on casts and remove them
            int componentCount = 0;
            for (int i = 0; i < p.orbs.size(); i++) {
                if (!(p.orbs.get(i) instanceof EmptyOrbSlot)) {
                    componentCount++;
                    AbstractComponent o = (AbstractComponent) p.orbs.get(i);
                    o.onCast();
                    p.orbs.set(i, new EmptyOrbSlot());
                    p.orbs.get(i).setSlot(i, p.maxOrbs);
                }

            }
            //update concentration if we actually found components
            if (componentCount > 0) {
                p.powers.get(index).flash();
                //p.powers.get(index).reducePower(p.powers.get(index).amount);
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, p.powers.get(index)));
            } else {
                AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, ((TheSorceress) p).getNoCastText(), true));
            }
            isDone = true;
        }
    }
}
