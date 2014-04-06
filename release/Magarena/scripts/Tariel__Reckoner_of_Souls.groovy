[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Removal),
        "Animate"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [new MagicTapEvent(source)];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.TARGET_OPPONENT,
                this,
                "PN chooses a creature card at random from target opponent's\$ graveyard. PN puts that card onto the battlefield under PN's control."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game, {
                final MagicPlayer opponent ->
                final List<MagicCard> targets = game.filterCards(opponent,MagicTargetFilterFactory.TARGET_CREATURE_CARD_FROM_GRAVEYARD);
                final MagicRandom rng = new MagicRandom(opponent.getGraveyard().getStateId());
                if (targets.isEmpty() == false) {
                    final int index = rng.nextInt(targets.size());
                    final MagicCard card = targets.get(index);
                    game.doAction(new MagicReanimateAction(
                        card,
                        event.getPlayer()
                    ));
                }
            });
        }
    }
]
