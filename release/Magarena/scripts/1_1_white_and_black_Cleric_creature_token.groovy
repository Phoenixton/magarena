[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Removal),
        "Animate"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostEvent(source,"{3}{W}{B}{B}"),
                new MagicTapEvent(source),
                new MagicSacrificeEvent(source)
            ];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "Return a creature card named Deathpact Angel from PN's graveyard to the battlefield under his or her control."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final List<MagicCard> cards = game.filterCards(
                    event.getPlayer(),
                    MagicTargetFilterFactory.CARD_FROM_GRAVEYARD);
            for (final MagicCard card : cards) {
                if (card.getName().equals("Deathpact Angel")) {
                    game.doAction(new MagicReanimateAction(
                        card,
                        event.getPlayer()
                    ));
                    break;
                }
            }
        }
    }
]
