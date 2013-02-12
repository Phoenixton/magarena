package magic.card;

import magic.model.MagicDamage;
import magic.model.MagicGame;
import magic.model.MagicPermanent;
import magic.model.MagicPlayer;
import magic.model.MagicSubType;
import magic.model.action.MagicDealDamageAction;
import magic.model.action.MagicPermanentAction;
import magic.model.choice.MagicMayChoice;
import magic.model.choice.MagicTargetChoice;
import magic.model.event.MagicEvent;
import magic.model.target.MagicDamageTargetPicker;
import magic.model.trigger.MagicWhenOtherComesIntoPlayTrigger;

public class Tajuru_Archer {
    public static final MagicWhenOtherComesIntoPlayTrigger T = new MagicWhenOtherComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPermanent other) {
            final int numAllies = permanent.getController().getNrOfPermanentsWithSubType(MagicSubType.Ally);
            return (other.isFriend(permanent) &&
                    other.hasSubType(MagicSubType.Ally)) ?
                new MagicEvent(
                    permanent,
                    new MagicMayChoice(
                        MagicTargetChoice.NEG_TARGET_CREATURE_WITH_FLYING
                    ),
                    // estimated. Amount of damage can be different on resolution
                    new MagicDamageTargetPicker(numAllies),
                    this,
                    "PN may$ have SN deal " +
                    "damage to target creature with flying$ equal to " +
                    "the number of Allies he or she controls.") :
                MagicEvent.NONE;
        }
        
        @Override
        public void executeEvent(
                final MagicGame game,
                final MagicEvent event,
                final Object[] choiceResults) {
            if (MagicMayChoice.isYesChoice(choiceResults[0])) {
                event.processTargetPermanent(game,choiceResults,1,new MagicPermanentAction() {
                    public void doAction(final MagicPermanent creature) {
                        final MagicPlayer player = event.getPlayer();
                        final int amount =
                                player.getNrOfPermanentsWithSubType(MagicSubType.Ally);
                        if (amount > 0) {
                            final MagicDamage damage = new MagicDamage(
                                    event.getPermanent(),
                                    creature,
                                    amount,
                                    false);
                            game.doAction(new MagicDealDamageAction(damage));
                        }
                    }
                });
            }            
        }        
    };
}
