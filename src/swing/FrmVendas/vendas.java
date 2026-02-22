package swing.FrmVendas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Model.cliente.clienteBeans;
import Model.cliente.clienteDao;
import Model.funcionario.funcionarioBeans;
import Model.funcionario.funcionarioDao;
import Model.produto.produtoBeans;
import Model.produto.produtoDao;
import Model.vendas.pedidosBeans;
import Model.vendas.pedidosDao;
import Model.vendas.vendasBeans;
import Model.vendas.vendasDao;

import java.awt.RenderingHints;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

public class vendas  extends JFrame{
    pedidosBeans ped = new pedidosBeans();
    pedidosDao daoP = new pedidosDao();
    vendasBeans v = new vendasBeans();
    vendasDao daoV = new vendasDao();
    ArrayList<vendasBeans> listaVendas = new ArrayList<>();
    private Image imagemLogo;
    private ImageIcon iconeVendas, iconeNovo, iconeSalvar, iconeEditar, iconeExcluir, iconeGerarRelatorio, iconePesquisa;
    private JTabbedPane panelPai;
    private JPanel panelRegistroVenda, panelVendas;
    private JButton btNovo, btSalvar, btEditar, btExcluir, btGerarRelatorioPdf, btPesquisaCliente, btPesquisaProduto, btPesquisaFuncionario;
    private JLabel lbCodigo, lbIdCliente, lbCliente, lbIdProduto, lbProduto, lbIdFuncionario, lbFuncionario, lbQtde, lbStatus, lbDia;
    private JTextField tfCodigo, tfIdCliente, tfCliente, tfIdProduto, tfProduto, tfIdFuncionario, tfFuncionario, tfQtde, tfDia;
    private JComboBox<String> cbStatus;
    private JTable tableVendas;
    private JScrollPane scrollVendas;
    public vendas() {
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(tela.width, tela.height-60);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        iconeVendas = new ImageIcon("./src/Img/vendasIcon.png");
        this.setIconImage(iconeVendas.getImage());

        panelPai = new JTabbedPane();
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.put("TabbedPane.contentAreaColor", Color.WHITE);
        UIManager.put("TabbedPane.borderHightlightColor", Color.WHITE);
        UIManager.put("TabbedPane.darkShadow", Color.WHITE);
        UIManager.put("TabbedPane.light", Color.WHITE);
        UIManager.put("TabbedPane.shadow", Color.WHITE);
        panelPai.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
                
            }
        });

        iconeNovo = new ImageIcon("./src/Img/novo.png");
        iconeSalvar = new ImageIcon("./src/Img/salvar.png");
        iconeEditar = new ImageIcon("./src/Img/editar.png");
        iconeExcluir = new ImageIcon("./src/Img/excluir.png");
        iconeGerarRelatorio = new ImageIcon("./src/Img/Relatorio.png");
        iconePesquisa = new ImageIcon("./src/Img/pesquisa.png");

        btNovo = new JButton("", iconeNovo){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g2);
                g2.dispose();
            }
        };
        btSalvar = new JButton("", iconeSalvar){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g2);
                g2.dispose();
            }
        };
        btEditar = new JButton("", iconeEditar){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g2);
                g2.dispose();
            }
        };
        btExcluir = new JButton("", iconeExcluir){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g2);
                g2.dispose();
            }
        };
        btGerarRelatorioPdf = new JButton("", iconeGerarRelatorio){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g2);
                g2.dispose();
            }
        };
        btPesquisaCliente = new JButton("", iconePesquisa){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g2);
                g2.dispose();
            }
        };
        btPesquisaProduto = new JButton("", iconePesquisa){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g2);
                g2.dispose();
            }
        };
        btPesquisaFuncionario = new JButton("", iconePesquisa){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g2);
                g2.dispose();
            }
        };

        // setando o background dos botões
        btNovo.setBackground(Color.decode("#292e39"));
        btSalvar.setBackground(Color.decode("#292e39"));
        btEditar.setBackground(Color.decode("#292e39"));
        btExcluir.setBackground(Color.decode("#292e39"));
        btGerarRelatorioPdf.setBackground(Color.decode("#292e39"));
        btPesquisaCliente.setBackground(Color.decode("#382b73"));
        btPesquisaProduto.setBackground(Color.decode("#382b73"));
        btPesquisaFuncionario.setBackground(Color.decode("#382b73"));

        // setando as coordenadas dos botões
        btNovo.setBounds(10, 10, 36, 36);
        btSalvar.setBounds(66, 10, 36, 36);
        btEditar.setBounds(122, 10, 36, 36);
        btExcluir.setBounds(178, 10, 36, 36);
        btGerarRelatorioPdf.setBounds(10, 10, 36, 36);
        btPesquisaCliente.setBounds(115, 155, 32, 32);
        btPesquisaProduto.setBounds(115, 225, 32, 32);
        btPesquisaFuncionario.setBounds(115, 295, 32, 32);

        // tirando as coisas dos botões para deixar eles redondos
        btNovo.setContentAreaFilled(false);       
        btSalvar.setContentAreaFilled(false);
        btEditar.setContentAreaFilled(false);
        btExcluir.setContentAreaFilled(false);
        btGerarRelatorioPdf.setContentAreaFilled(false);
        btPesquisaCliente.setContentAreaFilled(false);
        btPesquisaProduto.setContentAreaFilled(false);
        btPesquisaFuncionario.setContentAreaFilled(false);
        
        btNovo.setFocusPainted(false);       
        btSalvar.setFocusPainted(false);
        btEditar.setFocusPainted(false);
        btExcluir.setFocusPainted(false);
        btGerarRelatorioPdf.setFocusPainted(false);
        btPesquisaCliente.setFocusPainted(false);
        btPesquisaProduto.setFocusPainted(false);
        btPesquisaFuncionario.setFocusPainted(false);
        
        btNovo.setBorderPainted(false);       
        btSalvar.setBorderPainted(false);
        btEditar.setBorderPainted(false);
        btExcluir.setBorderPainted(false);
        btGerarRelatorioPdf.setBorderPainted(false);
        btPesquisaCliente.setBorderPainted(false);
        btPesquisaProduto.setBorderPainted(false);
        btPesquisaFuncionario.setBorderPainted(false);

        // adicionando o hand curso no mouse
        btNovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));       
        btSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btGerarRelatorioPdf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btPesquisaCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btPesquisaProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btPesquisaFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // adicionando o tooltip nos botoes
        btNovo.setToolTipText("Novo");       
        btSalvar.setToolTipText("Salvar ou Atualizar");
        btEditar.setToolTipText("Editar");
        btExcluir.setToolTipText("Excluir");
        btGerarRelatorioPdf.setToolTipText("Gerar relatório pdf");
        btPesquisaCliente.setToolTipText("Pesquisar Cliente");
        btPesquisaProduto.setToolTipText("Pesquisar Produto");
        btPesquisaFuncionario.setToolTipText("Pesquisar Funcionário");

        // ações dos botões
        btPesquisaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // aqui ele vai chamar o metodo que vai executar um jframe e gg
                buscarCliente();
            }   
        });
        btPesquisaProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProduto();
            }            
        });
        btPesquisaFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarFuncionario();
            }      
        });
        btNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfCodigo.setText("NOVO");
                habilitarCampos();
                btPesquisaCliente.doClick();
            }            
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (tfCodigo.getText().equals("NOVO")) {
                        inserir();
                        desabilitarCampos();
                        limparCampos();
                    } else if(tfCodigo.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Clique no botão Novo para inserir,\n ou para atualiza clique na lista,\n depois clique no cliente que deseja atualizar", "", JOptionPane.WARNING_MESSAGE);
                    } else {
                        update();
                        desabilitarCampos();
                        limparCampos();
                    }                   
               } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível gravar\n Erro no "+ex.getMessage(), "Erro na hora de gravar", JOptionPane.ERROR_MESSAGE);    
               }
            }            
        });
        btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habilitarCampos();
                btPesquisaCliente.doClick();
            }            
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int verificar = JOptionPane.showConfirmDialog(null, "Para que você delete um cliente é necessário primeiro deletar a venda!!\nDeseja continuar com o processo?", "", JOptionPane.YES_NO_OPTION);
                if(verificar == 0) {
                    int escolha = JOptionPane.showConfirmDialog(null, "Deseja deletar este cliente?", "", JOptionPane.YES_NO_OPTION);
                    if (escolha == 0) {
                        if(tfCodigo.getText().equals("NOVO") || tfCodigo.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "O campo de texto ID tem que ter um número de um id do banco de dados", "", JOptionPane.WARNING_MESSAGE);
                        } else {
                            deletar();
                            limparCampos();
                        }
                    }
                }
            }     
        });
        btGerarRelatorioPdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarListaPdf();
            }            
        });

        // Iniciando os JtxtF
        tfCodigo = new JTextField();
        tfIdCliente = new JTextField();
        tfCliente = new JTextField();
        tfIdProduto = new JTextField();
        tfProduto = new JTextField();
        tfIdFuncionario = new JTextField();
        tfFuncionario = new JTextField();
        tfQtde = new JTextField();
        tfDia = new JTextField(dateToday());

        //.setEditable(false);
        tfCodigo.setEditable(false);
        tfIdCliente.setEditable(false);
        tfCliente.setEditable(false);
        tfIdProduto.setEditable(false);
        tfProduto.setEditable(false);
        tfIdFuncionario.setEditable(false);
        tfFuncionario.setEditable(false);
        tfQtde.setEditable(false);
        tfDia.setEditable(false);

        // setando coordenadas, cor do texto, cor de fundo, borda e o caret do tf
        tfCodigo.setBounds(10, 90, 100, 25);
        tfCodigo.setForeground(Color.decode("#88c0d0"));
        tfCodigo.setBackground(Color.decode("#292e39"));
        tfCodigo.setCaretColor(Color.decode("#88c0d0"));
        tfCodigo.setBorder(null);

        tfIdCliente.setBounds(10, 160, 100, 25);
        tfIdCliente.setForeground(Color.decode("#88c0d0"));
        tfIdCliente.setBackground(Color.decode("#292e39"));
        tfIdCliente.setCaretColor(Color.decode("#88c0d0"));
        tfIdCliente.setBorder(null);

        tfIdProduto.setBounds(10, 230, 100, 25);
        tfIdProduto.setForeground(Color.decode("#88c0d0"));
        tfIdProduto.setBackground(Color.decode("#292e39"));
        tfIdProduto.setCaretColor(Color.decode("#88c0d0"));
        tfIdProduto.setBorder(null);

        tfIdFuncionario.setBounds(10, 300, 100, 25);
        tfIdFuncionario.setForeground(Color.decode("#88c0d0"));
        tfIdFuncionario.setBackground(Color.decode("#292e39"));
        tfIdFuncionario.setCaretColor(Color.decode("#88c0d0"));
        tfIdFuncionario.setBorder(null);

        tfCliente.setBounds(150, 160, 250, 25);
        tfCliente.setForeground(Color.decode("#88c0d0"));
        tfCliente.setBackground(Color.decode("#292e39"));
        tfCliente.setCaretColor(Color.decode("#88c0d0"));
        tfCliente.setBorder(null);

        tfProduto.setBounds(150, 230, 250, 25);
        tfProduto.setForeground(Color.decode("#88c0d0"));
        tfProduto.setBackground(Color.decode("#292e39"));
        tfProduto.setCaretColor(Color.decode("#88c0d0"));
        tfProduto.setBorder(null);

        tfFuncionario.setBounds(150, 300, 250, 25);
        tfFuncionario.setForeground(Color.decode("#88c0d0"));
        tfFuncionario.setBackground(Color.decode("#292e39"));
        tfFuncionario.setCaretColor(Color.decode("#88c0d0"));
        tfFuncionario.setBorder(null);

        tfQtde.setBounds(10, 370, 80, 25);
        tfQtde.setForeground(Color.decode("#88c0d0"));
        tfQtde.setBackground(Color.decode("#292e39"));
        tfQtde.setCaretColor(Color.decode("#88c0d0"));
        tfQtde.setBorder(null);

        tfDia.setBounds(10, 440, 100, 25);
        tfDia.setForeground(Color.decode("#88c0d0"));
        tfDia.setBackground(Color.decode("#292e39"));
        tfDia.setCaretColor(Color.decode("#88c0d0"));
        tfDia.setBorder(null);

        // Iniciando o ComboBox
        cbStatus = new JComboBox<String>();

        cbStatus.addItem("Pago");
        cbStatus.addItem("A pagar");

        cbStatus.setBackground(Color.decode("#292e39"));
        cbStatus.setForeground(Color.decode("#88c0d0"));
        cbStatus.setBounds(120, 370, 100, 25);

        // iniciando os Labels
        lbCodigo = new JLabel("Id.:");
        lbIdCliente = new JLabel("Id cliente.:");
        lbCliente = new JLabel("Cliente.:");
        lbIdProduto = new JLabel("Id produto.:");
        lbProduto = new JLabel("Produto.:");
        lbIdFuncionario = new JLabel("Id funcionário.:");
        lbFuncionario = new JLabel("Funcionário.:");
        lbQtde = new JLabel("Quantidade.:");
        lbStatus = new JLabel("Status da venda.:");
        lbDia = new JLabel("Data da compra.:");

        // mandando coisas padrões
        lbCodigo.setForeground(Color.decode("#ffffff"));
        lbIdCliente.setForeground(Color.decode("#ffffff"));
        lbCliente.setForeground(Color.decode("#ffffff"));
        lbIdProduto.setForeground(Color.decode("#ffffff"));
        lbProduto.setForeground(Color.decode("#ffffff"));
        lbIdFuncionario.setForeground(Color.decode("#ffffff"));
        lbFuncionario.setForeground(Color.decode("#ffffff"));
        lbQtde.setForeground(Color.decode("#ffffff"));
        lbStatus.setForeground(Color.decode("#ffffff"));
        lbDia.setForeground(Color.decode("#ffffff"));

        // setando as localização do labels
        lbCodigo.setBounds(10, 70, 20, 25);
        lbIdCliente.setBounds(10, 140, 70, 25);
        lbIdProduto.setBounds(10, 210, 70, 25);
        lbIdFuncionario.setBounds(10, 280, 90, 25);
        lbCliente.setBounds(150, 140, 90, 25);
        lbProduto.setBounds(150, 210, 90, 25);
        lbFuncionario.setBounds(150, 280, 90, 25);
        lbQtde.setBounds(10, 350, 90, 25);
        lbDia.setBounds(10, 420, 120, 25);
        lbStatus.setBounds(120, 350, 120, 25);

        panelRegistroVenda = new JPanel();
        panelRegistroVenda.setLayout(null);
        panelRegistroVenda.setBackground(Color.decode("#382b73"));
        panelRegistroVenda.add(btNovo);
        panelRegistroVenda.add(btSalvar);
        panelRegistroVenda.add(btEditar);
        panelRegistroVenda.add(btExcluir);
        panelRegistroVenda.add(btGerarRelatorioPdf);
        panelRegistroVenda.add(lbCodigo);
        panelRegistroVenda.add(tfCodigo);
        panelRegistroVenda.add(lbIdCliente);
        panelRegistroVenda.add(tfIdCliente);
        panelRegistroVenda.add(lbIdProduto);
        panelRegistroVenda.add(tfIdProduto);
        panelRegistroVenda.add(lbIdFuncionario);
        panelRegistroVenda.add(tfIdFuncionario);
        panelRegistroVenda.add(btPesquisaCliente);
        panelRegistroVenda.add(btPesquisaProduto);
        panelRegistroVenda.add(btPesquisaFuncionario);
        panelRegistroVenda.add(tfCliente);
        panelRegistroVenda.add(lbCliente);
        panelRegistroVenda.add(lbProduto);
        panelRegistroVenda.add(tfProduto);
        panelRegistroVenda.add(lbFuncionario);
        panelRegistroVenda.add(tfFuncionario);
        panelRegistroVenda.add(lbQtde);
        panelRegistroVenda.add(tfQtde);
        panelRegistroVenda.add(lbDia);
        panelRegistroVenda.add(tfDia);
        panelRegistroVenda.add(lbStatus);
        panelRegistroVenda.add(cbStatus);

        // criando a lista do panel de vendas
        String[] colunas = {"Id", "Id cliente", "Cliente", "Id produto", "Produto", "Valor", "Quantidade comprada", "Id Funcionário", "Funcionário", "Status de pagamento", "Data da compra"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        listaVendas = daoV.listarVendas();
        for(vendasBeans ve: listaVendas) {
            String pago = "Pago";
            String nPago = "Não pago";
            if(ve.isStatusPagamentoVendas() == true){
                Object[] linha = {
                    ve.getIdPedidoVendas(),
                    ve.getIdClienteVendas(),
                    ve.getNomeClienteVendas(),
                    ve.getIdProdutoVendas(),
                    ve.getNomeProdutoVendas(),
                    ve.getValorProdutoVendas(),
                    ve.getQtdeCompradaVendas(),
                    ve.getIdFuncionarioVendas(),
                    ve.getNomeFuncionarioVendas(),
                    pago,
                    ve.getDataCompraVendas().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                };
                modelo.addRow(linha);
            } else {
                Object[] linha = {
                    ve.getIdPedidoVendas(),
                    ve.getIdClienteVendas(),
                    ve.getNomeClienteVendas(),
                    ve.getIdProdutoVendas(),
                    ve.getNomeProdutoVendas(),
                    ve.getValorProdutoVendas(),
                    ve.getQtdeCompradaVendas(),
                    ve.getIdFuncionarioVendas(),
                    ve.getNomeFuncionarioVendas(),
                    nPago,
                    ve.getDataCompraVendas().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                };
                modelo.addRow(linha);
            }
        }

        tableVendas = new JTable(modelo);
        tableVendas.setBackground(Color.decode("#292e39"));
        tableVendas.setForeground(Color.decode("#88c0d0"));
        tableVendas.getSelectionModel().addListSelectionListener(e -> {
            if(e.getValueIsAdjusting());
            int linha = tableVendas.getSelectedRow();
            if(linha == -1) return;
            int linhaModelo = tableVendas.convertRowIndexToModel(linha);

            tfCodigo.setText(tableVendas.getModel().getValueAt(linhaModelo, 0).toString());
            tfIdCliente.setText(tableVendas.getModel().getValueAt(linhaModelo, 1).toString());
            tfCliente.setText(tableVendas.getModel().getValueAt(linhaModelo, 2).toString());
            tfIdProduto.setText(tableVendas.getModel().getValueAt(linhaModelo, 3).toString());
            tfProduto.setText(tableVendas.getModel().getValueAt(linhaModelo, 4).toString());
            tfQtde.setText(tableVendas.getModel().getValueAt(linhaModelo, 6).toString());
            tfIdFuncionario.setText(tableVendas.getModel().getValueAt(linhaModelo, 7).toString());
            tfFuncionario.setText(tableVendas.getModel().getValueAt(linhaModelo, 8).toString());
            tfDia.setText(tableVendas.getModel().getValueAt(linhaModelo, 10).toString());

            panelPai.setSelectedComponent(panelRegistroVenda);
        });

        scrollVendas = new JScrollPane(tableVendas);
        scrollVendas.setBounds(0, 100, tela.width, 700);
        scrollVendas.getViewport().setOpaque(false);
        scrollVendas.setBorder(BorderFactory.createEmptyBorder());
        scrollVendas.setOpaque(false);

        panelVendas = new JPanel();
        panelVendas.setLayout(null);
        panelVendas.setBackground(Color.decode("#382b73"));
        panelVendas.add(scrollVendas);
        panelVendas.add(btGerarRelatorioPdf);

        panelPai.add(panelRegistroVenda, "Registrar");
        panelPai.add(panelVendas, "Lista das Vendas");
        this.add(panelPai);
        this.setVisible(true);
    }
    //  habilita os tf
    private void habilitarCampos(){
        tfQtde.setEditable(true);
        tfDia.setEditable(true);
    }
    // desabilita os tf
    private void desabilitarCampos(){
        tfQtde.setEditable(false);
        tfDia.setEditable(false);
    }
    private void limparCampos(){
        tfCodigo.setText("");
        tfIdCliente.setText("");
        tfCliente.setText("");
        tfIdProduto.setText("");
        tfProduto.setText("");
        tfIdFuncionario.setText("");
        tfFuncionario.setText("");
        tfQtde.setText("");
        tfDia.setText("");
    }
    // inserir um nova venda
    private void inserir() {
        try {
            ped.setIdCliente(Long.valueOf(tfIdCliente.getText().trim()));
            ped.setIdProduto(Long.valueOf(tfIdProduto.getText().trim()));
            ped.setIdFuncionario(Long.valueOf(tfIdFuncionario.getText().trim()));
            ped.setQtdeComprada(Long.valueOf(tfQtde.getText().trim()));
            if(cbStatus.getSelectedItem().equals("Pago")){
                ped.setStatusPagamentos(true);
            } else {
                ped.setStatusPagamentos(false);
            }
            ped.setDataCompra(LocalDate.parse(tfDia.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            daoP.inserirPedido(ped);
            JOptionPane.showMessageDialog(null, "Gravado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de vendas para que a listagem dos vendas recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    private void update() {
        try {
            ped.setIdPedido(Long.valueOf(tfCodigo.getText().trim()));
            ped.setIdCliente(Long.valueOf(tfIdCliente.getText().trim()));
            ped.setIdProduto(Long.valueOf(tfIdProduto.getText().trim()));
            ped.setIdFuncionario(Long.valueOf(tfIdFuncionario.getText().trim()));
            ped.setQtdeComprada(Long.valueOf(tfQtde.getText().trim()));
            if(cbStatus.getSelectedItem().equals("Pago")){
                ped.setStatusPagamentos(true);
            } else {
                ped.setStatusPagamentos(false);
            }
            ped.setDataCompra(LocalDate.parse(tfDia.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            daoP.updatePedidos(ped);
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de vendas para que a listagem dos vendas recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    private void deletar() {
        try {
            ped.setIdPedido(Long.valueOf(tfCodigo.getText().trim()));
            daoP.deletePedidos(ped);
            JOptionPane.showMessageDialog(null, "Deletado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de vendas para que a listagem dos vendas recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    // seta a data pra hj
    private String dateToday(){
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return today;
    }
    // o busca serve para buscar o cliente e mandar as coisas para os tfs
    private void buscarCliente() {
        // primeiro eu inicio as classes para poder usar
        clienteDao daoCl = new clienteDao();
        ArrayList<clienteBeans> listaCliente = new ArrayList<>();
        // crio o jtable para colocar a informações e o scroll para rolar
        JTable tableCliente;    JScrollPane scrollCliente;
        
        // crio o jframe para poder fazer esse processo
        JFrame buscaCliente = new JFrame("#### Busca de Cliente ####");
        buscaCliente.setSize(600, 600);
        buscaCliente.setLocationRelativeTo(null);
        buscaCliente.setLayout(null);
        buscaCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buscaCliente.getContentPane().setBackground(Color.decode("#382b73"));
        ImageIcon iconeBusca = new ImageIcon("./src/Img/clientesIcon.png");
        buscaCliente.setIconImage(iconeBusca.getImage());

        // crio um vetor para ser o titulo
        String[] colunasCliente = {"Id", "Nome"};
        //crio o table model para colocar as informações
        DefaultTableModel modeloCliente = new DefaultTableModel(colunasCliente, 0);
        // crio o laço para poder listar
        listaCliente = daoCl.listarCliente();
        for(clienteBeans cl : listaCliente){
            // crio um vetor object para ficar as minhas coisas
            Object[] linhaCliente = {
                cl.getIdCliente(),
                cl.getNomeCliente()
            };
            // depois do laço adiciono no model cada linha
            modeloCliente.addRow(linhaCliente);
        }
        //depois adiciono no jtable
        tableCliente = new JTable(modeloCliente);
        tableCliente.setBackground(Color.decode("#292e39"));
        tableCliente.setForeground(Color.decode("#88c0d0"));
        // aqui ele vai pegar a informação na linha e coluna que vc clicou para mandar as coisas pros tfs
        tableCliente.getSelectionModel().addListSelectionListener(e -> {
            if(e.getValueIsAdjusting());
            int linha = tableCliente.getSelectedRow();

            if(linha == -1) return;
            int linhaModelo = tableCliente.convertRowIndexToModel(linha);

            tfIdCliente.setText(tableCliente.getModel().getValueAt(linhaModelo, 0).toString());
            tfCliente.setText(tableCliente.getModel().getValueAt(linhaModelo, 1).toString());

            // depois de mandar as informações ele vai fechar
            buscaCliente.dispose();
        });

        // Aqui eu adiciono no scroll para ele ter a rolagem para ver mais clientes
        scrollCliente = new JScrollPane(tableCliente);
        scrollCliente.setBounds(0, 10, 580, 500);
        scrollCliente.getViewport().setOpaque(false);
        scrollCliente.setBorder(BorderFactory.createEmptyBorder());
        scrollCliente.setOpaque(false);

        // adiciono o scroll no frame para sim poder aparecer tudo e depois eu torno ele visivel
        buscaCliente.add(scrollCliente);
        buscaCliente.setVisible(true);
    }
    // o busca serve para buscar o produto e mandar as coisas para os tfs
    private void buscarProduto() {
        produtoDao daoPr = new produtoDao();
        ArrayList<produtoBeans> listaProduto = new ArrayList<>();
        JTable tableProduto;    JScrollPane scrollProduto;

        JFrame buscaProduto = new JFrame("#### Busca de Produto ####");
        buscaProduto.setSize(600, 600);
        buscaProduto.setLocationRelativeTo(null);
        buscaProduto.setLayout(null);
        buscaProduto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buscaProduto.getContentPane().setBackground(Color.decode("#382b73"));
        ImageIcon iconeBusca = new ImageIcon("./src/Img/produtosIcon.png");
        buscaProduto.setIconImage(iconeBusca.getImage());

        String[] colunasProduto = {"Id", "Produto"};
        DefaultTableModel modeloProduto = new DefaultTableModel(colunasProduto, 0);
        listaProduto = daoPr.listarProduto();
        for(produtoBeans pr : listaProduto) {
            Object[] linhaProduto = {
                pr.getIdProduto(),
                pr.getNomeProduto()
            };
            modeloProduto.addRow(linhaProduto);
        }
        tableProduto = new JTable(modeloProduto);
        tableProduto.setBackground(Color.decode("#292e39"));
        tableProduto.setForeground(Color.decode("#88c0d0"));
        tableProduto.getSelectionModel().addListSelectionListener(e -> {
            if(e.getValueIsAdjusting());
            int linha = tableProduto.getSelectedRow();
            if (linha == -1) return;
            int linhaModelo = tableProduto.convertRowIndexToModel(linha);

            tfIdProduto.setText(tableProduto.getModel().getValueAt(linhaModelo, 0).toString());
            tfProduto.setText(tableProduto.getModel().getValueAt(linhaModelo, 1).toString());

            buscaProduto.dispose();
        });

        scrollProduto = new JScrollPane(tableProduto);
        scrollProduto.setBounds(0, 10, 580, 500);
        scrollProduto.getViewport().setOpaque(false);
        scrollProduto.setBorder(BorderFactory.createEmptyBorder());
        scrollProduto.setOpaque(false);
        
        buscaProduto.add(scrollProduto);
        buscaProduto.setVisible(true);
    }
    // o busca serve para buscar o funcionario e mandar as coisas para os tfs
    private void buscarFuncionario() {
        funcionarioDao daoFu = new funcionarioDao();
        ArrayList<funcionarioBeans> listaFuncionario = new ArrayList<>();
        JTable tableFuncionario;    JScrollPane scrollFuncionario;

        JFrame buscaFuncionario = new JFrame("#### Busca de Funcionário ####");
        buscaFuncionario.setSize(600, 600);
        buscaFuncionario.setLocationRelativeTo(null);
        buscaFuncionario.setLayout(null);
        buscaFuncionario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buscaFuncionario.getContentPane().setBackground(Color.decode("#382b73"));
        ImageIcon iconeBusca = new ImageIcon("./src/Img/funcionarioIcon.png");
        buscaFuncionario.setIconImage(iconeBusca.getImage());

        String[] colunasFuncionario = {"Id", "Funcionário"};
        DefaultTableModel modeloFuncionario = new DefaultTableModel(colunasFuncionario, 0);
        listaFuncionario = daoFu.listaFuncionario();
        for(funcionarioBeans f: listaFuncionario) {
            Object[] linhaFuncionario = {
                f.getIdFuncionario(),
                f.getNomeFuncionario()
            };
            modeloFuncionario.addRow(linhaFuncionario);
        }
        tableFuncionario = new JTable(modeloFuncionario);
        tableFuncionario.setBackground(Color.decode("#292e39"));
        tableFuncionario.setForeground(Color.decode("#88c0d0"));
        tableFuncionario.getSelectionModel().addListSelectionListener(e -> {
            if(e.getValueIsAdjusting());
            int linha = tableFuncionario.getSelectedRow();
            if(linha == -1) return;
            int linhaModelo = tableFuncionario.convertRowIndexToModel(linha);

            tfIdFuncionario.setText(tableFuncionario.getModel().getValueAt(linhaModelo, 0).toString());
            tfFuncionario.setText(tableFuncionario.getModel().getValueAt(linhaModelo, 1).toString());

            buscaFuncionario.dispose();
        });

        scrollFuncionario = new JScrollPane(tableFuncionario);
        scrollFuncionario.setBounds(0, 10, 580, 500);
        scrollFuncionario.getViewport().setOpaque(false);
        scrollFuncionario.setBorder(BorderFactory.createEmptyBorder());
        scrollFuncionario.setOpaque(false);

        buscaFuncionario.add(scrollFuncionario);
        buscaFuncionario.setVisible(true);
    }
    // gera um pdf de vendas
    private void gerarListaPdf() {
        new ImageIcon("./src/Img/crm.png");
        FileDialog fileChooser = new FileDialog(this, "Salvar Relatório das Vendas", java.awt.FileDialog.SAVE);

        fileChooser.setFile("vendas.pdf");
        
        fileChooser.setFilenameFilter((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        fileChooser.setVisible(true);

        String diretorio = fileChooser.getDirectory();
        String nomeArquivo = fileChooser.getFile();

        if (diretorio != null && nomeArquivo != null) {
            File arquivoParaSalvar = new File(diretorio, nomeArquivo);

            if (!arquivoParaSalvar.getAbsolutePath().endsWith(".pdf")) {
                arquivoParaSalvar = new File(arquivoParaSalvar.getAbsolutePath() + ".pdf");                
            }

            Document documento = new Document(PageSize.A4.rotate());
            try {
                imagemLogo = Image.getInstance("./src/Img/crm.png");
                listaVendas = daoV.listarVendas();
                PdfWriter.getInstance(documento, new FileOutputStream(arquivoParaSalvar));
                documento.open();
                
                LocalDate data = LocalDate.now();
                String dataHj = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                
                documento.add(imagemLogo);
                documento.addAuthor("Aleph Enzo Guimarães da Silva");
                documento.add(new Paragraph("Relatório das vendas:                     Data:" + dataHj));
                documento.add(new Paragraph(" "));

                PdfPTable tabela = new PdfPTable(11);
                tabela.setWidthPercentage(90);

                float[] larguras = {4f, 10f, 15f, 10f, 12f, 7f, 5f, 10f, 15f, 8f, 12f};
                tabela.setWidths(larguras);

                // Cabeçalho
                tabela.addCell(new PdfPCell(new Paragraph("Id")));
                tabela.addCell(new PdfPCell(new Paragraph("Id cliente")));
                tabela.addCell(new PdfPCell(new Paragraph("Cliente")));
                tabela.addCell(new PdfPCell(new Paragraph("Id produto")));
                tabela.addCell(new PdfPCell(new Paragraph("Produto")));
                tabela.addCell(new PdfPCell(new Paragraph("Valor")));
                tabela.addCell(new PdfPCell(new Paragraph("Quantidade comprada")));
                tabela.addCell(new PdfPCell(new Paragraph("Id Funcionário")));
                tabela.addCell(new PdfPCell(new Paragraph("Funcionário")));
                tabela.addCell(new PdfPCell(new Paragraph("Status de pagamento")));
                tabela.addCell(new PdfPCell(new Paragraph("Data da compra")));

                for(vendasBeans vB: listaVendas) {
                    tabela.addCell(String.valueOf(vB.getIdPedidoVendas()));
                    tabela.addCell(String.valueOf(vB.getIdClienteVendas()));
                    tabela.addCell(vB.getNomeClienteVendas());
                    tabela.addCell(String.valueOf(vB.getIdProdutoVendas()));
                    tabela.addCell(vB.getNomeProdutoVendas());
                    tabela.addCell("R$ "+String.valueOf(vB.getValorProdutoVendas()).replace(".", ","));
                    tabela.addCell(String.valueOf(vB.getQtdeCompradaVendas()));
                    tabela.addCell(String.valueOf(vB.getIdFuncionarioVendas()));
                    tabela.addCell(vB.getNomeFuncionarioVendas());
                    if(vB.isStatusPagamentoVendas() == true) {
                        tabela.addCell("Pago");
                    } else {
                        tabela.addCell("Não pago");
                    }
                    tabela.addCell(vB.getDataCompraVendas().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
                
                documento.add(tabela);
                documento.close();

                System.out.println("Pdf gerado com sucesso!");

                int resposta = JOptionPane.showConfirmDialog(null, "Relatório gerado com sucesso!\nDeseja abrir o arquivo?", "Sucesso", JOptionPane.YES_NO_OPTION);
                
                if (resposta == JOptionPane.YES_OPTION) {
                    Desktop.getDesktop().open(arquivoParaSalvar);
                    System.out.println("Abrindo o relatório...");
                }
                
            } catch(Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                System.err.println("Pdf não gerado!");
            } finally {
                if (documento.isOpen()) {
                    documento.close();
                }
            }
        }
    }
}
