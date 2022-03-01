package theSorceress.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSorceress.SorceressMod;
import theSorceress.actions.AttuneAction;
import theSorceress.actions.InvokeAction;
import theSorceress.characters.TheSorceress;
import theSorceress.components.DummyComponent;
import theSorceress.components.Fear;

import static theSorceress.SorceressMod.makeCardPath;

public class DummyAttune extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = SorceressMod.makeID(DummyAttune.class.getSimpleName());
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


    public DummyAttune() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
       // baseDamage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new AttuneAction(p, new Fear()));
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
