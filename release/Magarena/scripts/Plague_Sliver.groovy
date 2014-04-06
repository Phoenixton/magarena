
def PlagueDamage = new MagicAtUpkeepTrigger() {
    @Override
    public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer upkeepPlayer) {
        return permanent.isController(upkeepPlayer) ?
            new MagicEvent(
                permanent,
                this,
                "SN deals 1 damage to PN."
            ) :
            MagicEvent.NONE;
    }
    @Override
    public void executeEvent(final MagicGame game, final MagicEvent event) {
        final MagicDamage damage=new MagicDamage(event.getSource(),event.getPlayer(),1);
        game.doAction(new MagicDealDamageAction(damage));
    }
};

[
    new MagicStatic(
        MagicLayer.Ability,
        MagicTargetFilterFactory.SLIVER
    ) {
        @Override
        public void modAbilityFlags(final MagicPermanent source,final MagicPermanent permanent,final Set<MagicAbility> flags) {    
            permanent.addAbility(PlagueDamage);
        }
    }
]
