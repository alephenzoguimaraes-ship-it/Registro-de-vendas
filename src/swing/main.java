package swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swing.FrmClientes.clientes;
import swing.FrmFuncionarios.funcionarios;
import swing.FrmProdutos.produtos;
import swing.FrmVendas.vendas;


public class main extends JFrame{
    private ImageIcon imagemLogo;
    private JButton btClientes, btFuncionario, btProdutos, btVendas;
    private JLabel lbDia;
    private JPanel panelBotoes, panelDia;
    private ImageIcon iconClientes, iconFuncionario, iconProdutos, iconVendas;
    public main() {
        //Configurações padrões
        System.out.println("Abrindo CRM ...");
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(tela.width, tela.height-40);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        imagemLogo = new ImageIcon("./src/Img/crm.png");
        this.setIconImage(imagemLogo.getImage());
        this.getContentPane().setBackground(Color.decode("#382b73"));
        this.setTitle("CRM");

        //setando as imagens dos botões
        iconClientes = new ImageIcon("./src/Img/clientesButton.png");
        iconFuncionario = new ImageIcon("./src/Img/funcionarioButton.png");
        iconProdutos = new ImageIcon("./src/Img/produtosButton.png");
        iconVendas = new ImageIcon("./src/Img/vendasButton.png"); 

        // formatando a data do dia para dd de MMMM de yyyy
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy"));
        // inicializando o label dia
        lbDia = new JLabel(data);

        // colocando o tooltip no label
        lbDia.setToolTipText("Data: "+data);

        // inicializandos os botões e colocando para eles ficarem redondos
        btClientes = new JButton("", iconClientes){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                super.paintComponent(g2);
                g2.dispose();
            }
        };
        btFuncionario = new JButton("", iconFuncionario){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                super.paintComponent(g2);
                g2.dispose();
            }
        };
        btProdutos = new JButton("", iconProdutos){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                super.paintComponent(g2);
                g2.dispose();
            }
        };
        btVendas = new JButton("", iconVendas){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                super.paintComponent(g2);
                g2.dispose();
            }
        };

        //tirando as coisas não necessárias
        btClientes.setContentAreaFilled(false);
        btClientes.setFocusPainted(false);
        btClientes.setBorderPainted(false);

        btFuncionario.setContentAreaFilled(false);
        btFuncionario.setFocusPainted(false);
        btFuncionario.setBorderPainted(false);

        btProdutos.setContentAreaFilled(false);
        btProdutos.setFocusPainted(false);
        btProdutos.setBorderPainted(false);

        btVendas.setContentAreaFilled(false);
        btVendas.setFocusPainted(false);
        btVendas.setBorderPainted(false);

        //colocando o tooltip nos botões
        btClientes.setToolTipText("Clientes");
        btFuncionario.setToolTipText("Funcionário");
        btProdutos.setToolTipText("Produtos");
        btVendas.setToolTipText("Vendas");

        //colocando cor de fundo nos butões
        btClientes.setBackground(Color.decode("#1f1d35"));
        btFuncionario.setBackground(Color.decode("#1f1d35"));
        btProdutos.setBackground(Color.decode("#1f1d35"));
        btVendas.setBackground(Color.decode("#1f1d35"));

        // adicionando o mouse com hand cursor
        btClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));        
        btProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));        
        btVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // adicionando função nos butões
        btClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("carregando clientes...");
                clientes c = new clientes();
                c.setVisible(true);
            }            
        });
        btFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("carregando funcionários...");
                funcionarios f = new funcionarios();
                f.setVisible(true);
            }            
        });
        btProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("carregando produtos...");
                produtos p = new produtos();
                p.setVisible(true);
            }
        });
        btVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("carregando vendas...");
                vendas v = new vendas();
                v.setVisible(true);
            }            
        });

        // adicionando os botões no panel
        panelBotoes = new JPanel(new GridLayout(0, 4, 20, 0));
        panelBotoes.setBackground(Color.decode("#382b73"));
        panelBotoes.add(btClientes);
        panelBotoes.add(btFuncionario);
        panelBotoes.add(btProdutos);
        panelBotoes.add(btVendas);
        panelBotoes.setBounds(756, 20, 400, 80);

        // adicionando o label dia no panel
        panelDia = new JPanel();
        panelDia.setBackground(Color.decode("#ffffff"));
        panelDia.setBounds(0, tela.height-130, tela.width, 25);
        panelDia.add(lbDia);
        
        //deixando o frame visivel e adicionando o panel de botões e o panel da data
        this.add(panelBotoes);
        this.add(panelDia);
        this.setVisible(true);
    }
    
}
