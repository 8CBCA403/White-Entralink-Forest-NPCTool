import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NPCForm extends JFrame {
    private ReadButton readButton;
    private WriteButton writeButton;
    private JFileChooser fileChooser;
    private JFrame mainFrame;
    private byte[] savFile;
    private JLabel[] NPCLabel;
    private NPCComboBox[] NPCBox;
    private JTextField[] GField;
    private JLabel[] FLabel;
    private JLabel WLabel;
    private JLabel BLabel;
    private JLabel GLabel;
    private JLabel SLabel;
    private NPC[] NPCList;

    public NPCForm() {
        super("白森NPC修改系统");
        this.mainFrame = this;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int startX = (screen.width - 600) / 2;
        int startY = (screen.height - 400) / 2;
        setBounds(startX, startY, 600, 400);
        setVisible(true);
        setDefaultCloseOperation(3);
        setResizable(false);
        setLayout(null);
        JPanel ButtonPanel = new JPanel();
        JPanel PresentNpcPanel = new JPanel();
        ButtonPanel.setBounds(0, 0, 110, 400);
        ButtonPanel.setLayout((LayoutManager) null);
        PresentNpcPanel.setBounds(110, 0, 480, 370);
        PresentNpcPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "表NPC"));
        PresentNpcPanel.setLayout((LayoutManager) null);
        add(ButtonPanel);
        add(PresentNpcPanel);
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileSelectionMode(0);
        this.readButton = new ReadButton("读取存档");
        this.readButton.setBounds(10, 10, 90, 24);
        ButtonPanel.add(this.readButton);
        this.writeButton = new WriteButton("存档另存");
        this.writeButton.setBounds(10, 44, 90, 24);
        ButtonPanel.add(this.writeButton);
        this.NPCLabel = new JLabel[10];
        this.NPCBox = new NPCComboBox[10];
        this.FLabel = new JLabel[10];
        this.GField = new JTextField[10];
        for (int i = 0; i < this.NPCLabel.length; i++) {
            String[] npcString = new String[31];
            npcString[0] = new String("无");
            for (int j = 1; j < 31; j++) {
                npcString[j] = Integer.toString(j - 1);
            }
            this.NPCBox[i] = new NPCComboBox(npcString, i);
            this.NPCBox[i].setBounds(70, 50 + (25 * i), 50, 20);
            this.NPCLabel[i] = new JLabel("NPC" + (i + 1));
            this.NPCLabel[i].setBounds(10, 50 + (25 * i), 50, 20);
            this.GField[i] = new JTextField();
            this.GField[i].setBounds(360, 50 + (25 * i), 50, 20);
            this.FLabel[i] = new JLabel("无");
            this.FLabel[i].setBounds(140, 50 + (25 * i), 80, 20);
            PresentNpcPanel.add(this.NPCLabel[i]);
            PresentNpcPanel.add(this.GField[i]);
            PresentNpcPanel.add(this.FLabel[i]);
            PresentNpcPanel.add(this.NPCBox[i]);
        }
        this.SLabel = new JLabel("NPC代号");
        this.WLabel = new JLabel("对应白森PM");
        this.GLabel = new JLabel("好感度(0~100)");
        this.SLabel.setBounds(70, 20, 60, 20);
        this.WLabel.setBounds(140, 20, 100, 20);
        this.GLabel.setBounds(360, 20, 90, 20);
        PresentNpcPanel.add(this.SLabel);
        PresentNpcPanel.add(this.WLabel);
        PresentNpcPanel.add(this.GLabel);
        repaint();
        this.NPCList = new NPC[20];
    }

    /* renamed from: Modifier$ReadButton */
    /* loaded from: a.jar:Modifier$ReadButton.class */
    class ReadButton extends JButton implements ActionListener {
        public ReadButton(String s) {
            super(s);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            int result = NPCForm.this.fileChooser.showOpenDialog(NPCForm.this.mainFrame);
            if (result == 0) {
                String fileName = NPCForm.this.fileChooser.getSelectedFile().getPath();
                try {
                    FileInputStream reader = new FileInputStream(new File(fileName));
                    NPCForm.this.savFile = new byte[reader.available()];
                    reader.read(NPCForm.this.savFile);
                    reader.close();
                    int p = 129544;
                    for (int i = 0; i < NPCForm.this.NPCList.length; i++) {
                        byte b = NPCForm.this.savFile[p];
                        int p2 = p + 1;
                        byte b2 = NPCForm.this.savFile[p2];
                        int p3 = p2 + 1;
                        byte b3 = NPCForm.this.savFile[p3];
                        int p4 = p3 + 1;
                        byte b4 = NPCForm.this.savFile[p4];
                        int p5 = p4 + 1;
                        if (b == 1) {
                            NPCForm.this.NPCList[i] = new NPC(b2, b3, b4);
                        } else {
                            NPCForm.this.NPCList[i] = null;
                        }
                        p = p5 + 20;
                    }
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(NPCForm.this.mainFrame, "读存档时发生错误");
                }
                for (int i2 = 0; i2 < 10; i2++) {
                    if (NPCForm.this.NPCList[i2] == null) {
                        NPCForm.this.NPCBox[i2].setSelectedIndex(0);
                        NPCForm.this.FLabel[i2].setText("无");
                        NPCForm.this.GField[i2].setText("");
                    } else {
                        NPCForm.this.NPCBox[i2].setSelectedIndex(NPCForm.this.NPCList[i2].getNo() + 1);
                        NPCForm.this.FLabel[i2].setText(NPC.WhiteForestNPC[NPCForm.this.NPCList[i2].getNo()]);
                        NPCForm.this.GField[i2].setText(Integer.toString(NPCForm.this.NPCList[i2].getLp()));
                    }
                }
            }
        }
    }

    /* renamed from: Modifier$WriteButton */
    /* loaded from: a.jar:Modifier$WriteButton.class */
    class WriteButton extends JButton implements ActionListener {
        public WriteButton(String s) {
            super(s);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            int hp;
            if (NPCForm.this.savFile != null && NPCForm.this.savFile.length >= 277240) {
                int p1 = 129544;
                int p2 = 277000;
                for (int i = 0; i < 10; i++) {
                    if (NPCForm.this.NPCBox[i].getSelectedIndex() != 0) {
                        NPCForm.this.savFile[p1] = 1;
                        NPCForm.this.savFile[p2] = 1;
                        int p12 = p1 + 1;
                        int p22 = p2 + 1;
                        NPCForm.this.savFile[p12] = (byte) (NPCForm.this.NPCBox[i].getSelectedIndex() - 1);
                        NPCForm.this.savFile[p22] = (byte) (NPCForm.this.NPCBox[i].getSelectedIndex() - 1);
                        int p13 = p12 + 1;
                        int p23 = p22 + 1;
                        try {
                            hp = Integer.parseInt(NPCForm.this.GField[i].getText());
                        } catch (Exception e2) {
                            hp = 100;
                        }
                        NPCForm.this.savFile[p13] = (byte) hp;
                        NPCForm.this.savFile[p23] = (byte) hp;
                        int p14 = p13 + 1;
                        int p24 = p23 + 1;
                        NPCForm.this.savFile[p14] = 7;
                        NPCForm.this.savFile[p24] = 7;
                        p1 = p14 + 1;
                        p2 = p24 + 1;
                        for (int j = 0; j < 20; j++) {
                            NPCForm.this.savFile[p1] = 0;
                            NPCForm.this.savFile[p2] = 0;
                            p1++;
                            p2++;
                        }
                    } else {
                        for (int j2 = 0; j2 < 24; j2++) {
                            NPCForm.this.savFile[p1] = 0;
                            NPCForm.this.savFile[p2] = 0;
                            p1++;
                            p2++;
                        }
                    }
                }
                int result = NPCForm.this.fileChooser.showSaveDialog(NPCForm.this.mainFrame);
                if (result == 0) {
                    String fileName = NPCForm.this.fileChooser.getSelectedFile().getPath();
                    try {
                        FileOutputStream writer = new FileOutputStream(new File(fileName));
                        writer.write(NPCForm.this.savFile);
                        writer.close();
                        JOptionPane.showMessageDialog(NPCForm.this.mainFrame, "存档完毕");
                    } catch (Exception e3) {
                        JOptionPane.showMessageDialog(NPCForm.this.mainFrame, "写存档时错误");
                    }
                }
            }
        }
    }

    /* renamed from: Modifier$NPCComboBox */
    /* loaded from: a.jar:Modifier$NPCComboBox.class */
    class NPCComboBox extends JComboBox implements ActionListener {
        private int index;

        public NPCComboBox(String[] s, int i) {
            super(s);
            this.index = i;
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            int no = getSelectedIndex() - 1;
            if (no == -1) {
                NPCForm.this.FLabel[this.index].setText("无");
            }
            if (no >= 0 && no <= 29) {
                NPCForm.this.FLabel[this.index].setText(NPC.WhiteForestNPC[no]);
            }
        }
    }

    public static void main(String[] args) {
        new NPCForm();
    }
}
