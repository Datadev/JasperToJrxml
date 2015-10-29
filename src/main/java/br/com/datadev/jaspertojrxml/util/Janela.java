package br.com.datadev.jaspertojrxml.util;

/**
 *
 * @author Fabrício
 */
import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Janela {

    public static void selecionaTXT(final java.awt.event.FocusEvent evento) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JTextField ftxt = (JTextField) evento.getComponent();
                ftxt.selectAll();
            }
        });
    }

    public static void msgErro(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void msgAviso(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static int cfmSimNao(String mensagem, String titulo) {
        Object[] botoes = {"Sim", "Não"};
        return JOptionPane.showOptionDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
    }

    public static void trocaCicloFoco(Container comp) {
        HashSet teclasOld = new HashSet(comp.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        HashSet teclasNew = new HashSet(comp.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        teclasNew.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        comp.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, teclasNew);

        Janela.procuraButton(comp, teclasOld);
    }

    private static void procuraButton(Container container, HashSet teclas) {
        for (int cont = 0; cont < container.getComponentCount(); cont++) {
            Component comp = container.getComponent(cont);
            if (comp instanceof JButton) {
                comp.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, teclas);
            } else if (comp instanceof Container) {
                procuraButton((Container) comp, teclas);
            }
        }
    }
}
