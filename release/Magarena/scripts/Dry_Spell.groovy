[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                this,
                "SN deals 1 damage to each creature and each player."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final Collection<MagicPermanent> targets =
                game.filterPermanents(event.getPlayer(),MagicTargetFilterFactory.CREATURE);
            for (final MagicPermanent target : targets) {
                final MagicDamage damage=new MagicDamage(event.getSource(),target,1);
                game.doAction(new MagicDealDamageAction(damage));
            }
            for (final MagicPlayer player : game.getPlayers()) {
                final MagicDamage damage=new MagicDamage(event.getSource(),player,1);
                game.doAction(new MagicDealDamageAction(damage));
            }
        }
    }
]
