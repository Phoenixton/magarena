[
    new MagicCDA() {
        @Override
        public void modPowerToughness(final MagicGame game,final MagicPlayer player,final MagicPowerToughness pt) {
            final int size = player.getNrOfPermanents(MagicTargetFilterFactory.BLACK_PERMANENT_YOU_CONTROL);
            pt.set(size, size);
        }
    }
]
