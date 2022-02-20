package theSorceress.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theSorceress.powers.CommonPower;
import theSorceress.powers.Concentration;

public class CastAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractMonster m;
    public CastAction(final AbstractPlayer p, final AbstractMonster m)
    {
        this.p = p;
        this.m = m;
    }
    @Override
    public void update() {
        int damage = 0;
        for(AbstractPower pow: p.powers)
        {
            if(pow instanceof Concentration)
            {
                damage = pow.amount;
                pow.flash();
                pow.reducePower(damage);
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, pow));
            }
        }
       if(damage > 0)
            AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, damage), AttackEffect.FIRE));
        isDone = true;
    }
}
