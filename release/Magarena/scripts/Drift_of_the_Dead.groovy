[
    new MagicCDA() {
        @Override
        public void modPowerToughness(final MagicGame game,final MagicPlayer player,final MagicPowerToughness pt) {
            final int size = game.filterPermanents(player,MagicTargetFilterFactory.TARGET_SNOW_LAND_YOU_CONTROL).size();
            pt.set(size, size);
        }
    }
]
