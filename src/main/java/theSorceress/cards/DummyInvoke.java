package theSorceress.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSorceress.SorceressMod;
import theSorceress.actions.InvokeAction;
import theSorceress.characters.TheSorceress;
import theSorceress.components.DummyComponent;
import theSorceress.components.Fear;

import static theSorceress.SorceressMod.makeCardPath;

public class DummyInvoke extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = SorceressMod.makeID(DummyInvoke.class.getSimpleName());
    public static final String IMG = makeCardPath("Noimage.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSorceress.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;

//    private static final int DAMAGE = 0;
//    private static final int UPGRADE_PLUS_DMG = 0;

    // /STAT DECLARATION/


    public DummyInvoke() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
       // baseDamage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new InvokeAction(p, new Fear()));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
           // upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
