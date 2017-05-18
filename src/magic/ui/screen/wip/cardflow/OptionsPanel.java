package magic.ui.screen.wip.cardflow;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import magic.data.MagicIcon;
import magic.translate.MText;
import magic.ui.FontsAndBorders;
import magic.ui.dialog.prefs.ImageSizePresets;
import magic.ui.helpers.ImageHelper;
import magic.ui.screen.widget.ActionBarButton;
import magic.ui.screen.widget.BigDialButton;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
class OptionsPanel extends JPanel {

    // translatable UI text (prefix with _S).
    private static final String _S1 = "Scale";

    private static final Icon MENU_ICON =
            ImageHelper.getRecoloredIcon(MagicIcon.OPTION_MENU, Color.BLACK, Color.WHITE);

    private static final Icon CLOSE_MENU =
            ImageHelper.getRecoloredIcon(MagicIcon.CLOSE, Color.BLACK, Color.WHITE);

    private boolean isMenuOpen = false;
    private final BigDialButton scaleButton;
    private final ActionBarButton menuButton;
    private final ActionBarButton closeButton;
    private ImageSizePresets sizePreset;
    private final ScreenSettings settings;

    OptionsPanel(final CardFlowScreen listener, final ScreenSettings settings) {

        this.settings = settings;
        sizePreset = settings.getImageSizePreset();

        scaleButton = new BigDialButton(
                ImageSizePresets.values().length - 1,
                sizePreset.ordinal(),
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sizePreset = sizePreset.next();
                        sizePreset = sizePreset == ImageSizePresets.SIZE_ORIGINAL ? ImageSizePresets.SIZE_223x310 : sizePreset;
                        listener.setImageSize(sizePreset);
                    }
                }
        );

        menuButton = new ActionBarButton((ImageIcon) MENU_ICON, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doToggleMenuOptions();
            }
        });

        closeButton = new ActionBarButton((ImageIcon) CLOSE_MENU, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doToggleMenuOptions();
            }
        });

        setLayout(new MigLayout(
                "flowy, wrap 2, gap 0 2, insets 0 0 2 0, ax right, ay center"
        ));
        setLayout();

        setOpaque(false);
    }

    private void setLayout() {
        removeAll();
        if (isMenuOpen) {
            add(getLabel(MText.get(_S1)), "ax center");
            add(scaleButton, "h 24!, gapbottom 2");
            add(closeButton, "spany 2, gapbottom 2");
        } else {
            add(menuButton, "spany 2");
        }
        revalidate();
        repaint();
    }

    private void doToggleMenuOptions() {
        isMenuOpen = !isMenuOpen;
        setLayout();
    }

    private JLabel getLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(FontsAndBorders.FONT0);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        return lbl;
    }

    void saveSettings() {
        System.out.println("OptionsPanel.saveSettings");
        settings.setImageSizePreset(sizePreset);
        settings.save();
    }
}