package swing.FrmClientes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import Model.cliente.clienteBeans;
import Model.cliente.clienteDao;

public class clientes extends JFrame{
    clienteBeans cl = new clienteBeans();
    clienteDao dao = new clienteDao();
    ArrayList<clienteBeans> lista = new ArrayList<clienteBeans>();
    private Image imagemLogo;
    private ImageIcon iconeClientes, iconeNovo, iconeSalvar, iconeEditar, iconeExcluir, iconeGerarRelatorioPdf;
    private JTabbedPane panelPai;
    private JPanel panelCadastro, panelListar;
    private JScrollPane scrollTableListar;
    private JTextField tfCodigo, tfNome, tfIdade, tfCpfCnpj, tfTelefone;
    private JLabel lbCodigo, lbNome, lbIdade, lbCpFCnpj, lbFisicaJuridica, lbTelefone;
    private JButton btNovo, btSalvar, btEditar, btExcluir, btGerarRelatorioPdf;
    private JComboBox<String> cbFisicaJuridica;
    private JTable tbListaCliente;
    public clientes() {
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();        
        this.setSize(tela.width, tela.height-60);
        iconeClientes = new ImageIcon("./src/Img/clientesIcon.png");
        this.setIconImage(iconeClientes.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Clientes");
        
        // inicializando os icones
        iconeNovo = new ImageIcon("./src/Img/novo.png");
        iconeSalvar = new ImageIcon("./src/Img/salvar.png");
        iconeEditar = new ImageIcon("./src/Img/editar.png");
        iconeExcluir = new ImageIcon("./src/Img/excluir.png");
        iconeGerarRelatorioPdf = new ImageIcon("./src/Img/Relatorio.png");

        // iniciando os botões
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
        btGerarRelatorioPdf = new JButton("", iconeGerarRelatorioPdf){
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

        // setando as coordenadas dos botões
        btNovo.setBounds(10, 10, 36, 36);
        btSalvar.setBounds(66, 10, 36, 36);
        btEditar.setBounds(122, 10, 36, 36);
        btExcluir.setBounds(178, 10, 36, 36);
        btGerarRelatorioPdf.setBounds(10, 10, 36, 36);

        // tirando as coisas dos botões para deixar eles redondos
        btNovo.setContentAreaFilled(false);       
        btSalvar.setContentAreaFilled(false);
        btEditar.setContentAreaFilled(false);
        btExcluir.setContentAreaFilled(false);
        btGerarRelatorioPdf.setContentAreaFilled(false);
        
        btNovo.setFocusPainted(false);       
        btSalvar.setFocusPainted(false);
        btEditar.setFocusPainted(false);
        btExcluir.setFocusPainted(false);
        btGerarRelatorioPdf.setFocusPainted(false);
        
        btNovo.setBorderPainted(false);       
        btSalvar.setBorderPainted(false);
        btEditar.setBorderPainted(false);
        btExcluir.setBorderPainted(false);
        btGerarRelatorioPdf.setBorderPainted(false);

        // adicionando o hand curso no mouse
        btNovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));       
        btSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btGerarRelatorioPdf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // adicionando o tooltip nos botoes
        btNovo.setToolTipText("Novo");       
        btSalvar.setToolTipText("Salvar ou Atualizar");
        btEditar.setToolTipText("Editar");
        btExcluir.setToolTipText("Excluir");
        btGerarRelatorioPdf.setToolTipText("Gerar relatório pdf");

        // adicionando funções nos botões
        btNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfCodigo.setText("NOVO");
                habilitarCampos();
                tfNome.requestFocus();
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
                tfNome.requestFocus();
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

        // iniciando os textfield
        tfCodigo = new JTextField();
        tfNome = new JTextField();
        tfIdade = new JTextField();
        tfCpfCnpj = new JTextField();
        tfTelefone = new JTextField();

        // tirando a permissão do usuario de poder alterar o cliente
        tfCodigo.setEditable(false);
        tfNome.setEditable(false);
        tfIdade.setEditable(false);
        tfCpfCnpj.setEditable(false);
        tfTelefone.setEditable(false);
        
        // setando as coordenadas dos campos
        tfCodigo.setBounds(10, 90, 100, 25);
        tfCodigo.setForeground(Color.decode("#88c0d0"));
        tfCodigo.setBackground(Color.decode("#292e39"));
        tfCodigo.setCaretColor(Color.decode("#88c0d0"));
        tfCodigo.setBorder(null);
        
        tfNome.setBounds(10, 160, 150, 25);
        tfNome.setForeground(Color.decode("#88c0d0"));
        tfNome.setBackground(Color.decode("#292e39"));
        tfNome.setCaretColor(Color.decode("#88c0d0"));
        tfNome.setBorder(null);

        tfIdade.setBounds(10, 230, 60, 25);
        tfIdade.setForeground(Color.decode("#88c0d0"));
        tfIdade.setBackground(Color.decode("#292e39"));
        tfIdade.setCaretColor(Color.decode("#88c0d0"));
        tfIdade.setBorder(null);

        tfCpfCnpj.setBounds(10, 300, 100, 25);
        tfCpfCnpj.setForeground(Color.decode("#88c0d0"));
        tfCpfCnpj.setBackground(Color.decode("#292e39"));
        tfCpfCnpj.setCaretColor(Color.decode("#88c0d0"));
        tfCpfCnpj.setBorder(null);

        tfTelefone.setBounds(10, 440, 100, 25);
        tfTelefone.setForeground(Color.decode("#88c0d0"));
        tfTelefone.setBackground(Color.decode("#292e39"));
        tfTelefone.setCaretColor(Color.decode("#88c0d0"));
        tfTelefone.setBorder(null);

        // inicializando o combo box
        cbFisicaJuridica = new JComboBox<>();

        // adicionando os itens no combobox
        cbFisicaJuridica.addItem("Pessoa Física");
        cbFisicaJuridica.addItem("Pessoa Jurídica");

        // setando a cor do combobox
        cbFisicaJuridica.setBackground(Color.decode("#292e39"));
        cbFisicaJuridica.setForeground(Color.decode("#88c0d0"));

        // setando os bounds
        cbFisicaJuridica.setBounds(10, 370, 160, 25);

        // iniciando os labels
        lbCodigo = new JLabel("Id.:");
        lbNome = new JLabel("Nome.:");
        lbIdade = new JLabel("Idade.:");
        lbCpFCnpj = new JLabel("Cpf ou Cnpj.:");
        lbFisicaJuridica = new JLabel("Pessoa fisica ou jurídica.:");
        lbTelefone = new JLabel("Telefone.:");

        // setando as coordenadas dos labels
        lbCodigo.setBounds(10, 70, 50, 25);
        lbNome.setBounds(10, 140, 50, 25);
        lbIdade.setBounds(10, 210, 50, 25);
        lbCpFCnpj.setBounds(10, 280, 80, 25);
        lbFisicaJuridica.setBounds(10, 350, 160, 25);
        lbTelefone.setBounds(10, 420, 60, 25);

        // setando as cores das letras dos labels
        lbCodigo.setForeground(Color.decode("#ffffff"));
        lbNome.setForeground(Color.decode("#ffffff"));
        lbIdade.setForeground(Color.decode("#ffffff"));
        lbCpFCnpj.setForeground(Color.decode("#ffffff"));
        lbFisicaJuridica.setForeground(Color.decode("#ffffff"));
        lbTelefone.setForeground(Color.decode("#ffffff"));

        // iniciando o painel pai
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

        // iniciando o painel cadastro
        panelCadastro = new JPanel();
        panelCadastro.setBackground(Color.decode("#382b73"));
        panelCadastro.setSize(tela.width, tela.height-60);
        panelCadastro.setLayout(null);

        // adicionando os botões no painel de cadastro
        panelCadastro.add(btNovo);
        panelCadastro.add(btSalvar);
        panelCadastro.add(btEditar);
        panelCadastro.add(btExcluir);

        // adicionando os textfield no painel de cadastro
        panelCadastro.add(tfCodigo);
        panelCadastro.add(tfNome);
        panelCadastro.add(tfIdade);
        panelCadastro.add(tfCpfCnpj);
        panelCadastro.add(tfTelefone);

        // adicionando os labels no painel de cadastro
        panelCadastro.add(lbCodigo);
        panelCadastro.add(lbNome);
        panelCadastro.add(lbIdade);
        panelCadastro.add(lbCpFCnpj);
        panelCadastro.add(lbFisicaJuridica);
        panelCadastro.add(lbTelefone);

        // adicionando o combo box no painel de cadastro
        panelCadastro.add(cbFisicaJuridica);

        // iniciando o painel listar e colocando ele no scroll panel
        panelListar = new JPanel();
        panelListar.setBackground(Color.decode("#382b73"));
        panelListar.setSize(tela.width, tela.height-60);
        panelListar.setLayout(null);

        // criando tabela de lista
        String[] colunas = {"Id", "Nome", "Idade", "Cpf ou Cnpj", "Física ou Jurídica", "Telefone"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        lista = dao.listarCliente();
        for (clienteBeans c: lista) {
            Object[] linha = {
                c.getIdCliente(),
                c.getNomeCliente(),
                c.getIdadeCliente(),
                c.getCpfCnpjCliente(),
                c.getFisicaJuridicaCliente(),
                c.getTelefoneCliente()
            };
            modelo.addRow(linha);
        }
        tbListaCliente = new JTable();
        tbListaCliente.setModel(modelo);
        tbListaCliente.setBackground(Color.decode("#292e39"));
        tbListaCliente.setForeground(Color.decode("#88c0d0"));
        tbListaCliente.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()){
                int linha = tbListaCliente.getSelectedRow();

                if (linha == -1) return;

                    int linhaModelo = tbListaCliente.convertRowIndexToModel(linha);
                    
                    tfCodigo.setText(tbListaCliente.getModel().getValueAt(linhaModelo, 0).toString());
                    tfNome.setText(tbListaCliente.getModel().getValueAt(linhaModelo, 1).toString());
                    tfIdade.setText(tbListaCliente.getModel().getValueAt(linhaModelo, 2).toString());
                    tfCpfCnpj.setText(tbListaCliente.getModel().getValueAt(linhaModelo, 3).toString());
                    tfTelefone.setText(tbListaCliente.getModel().getValueAt(linhaModelo, 5).toString());

                    panelPai.setSelectedComponent(panelCadastro);
            }
        });

        scrollTableListar = new JScrollPane(tbListaCliente);
        

        //definindo as cordenadas da tabela
        scrollTableListar.setBounds(0, 100, tela.width, 700);
        scrollTableListar.getViewport().setOpaque(false);
        scrollTableListar.setBorder(BorderFactory.createEmptyBorder());
        scrollTableListar.setOpaque(false);
        panelListar.add(scrollTableListar);
        panelListar.add(btGerarRelatorioPdf);

        // adiciona coisas no painel
        panelPai.add(panelCadastro, "Cadastro");
        panelPai.add(panelListar, "Lista");
        this.add(panelPai);
        this.setVisible(true);
        
    }
    // habilita os textfields
    private void habilitarCampos(){
        tfNome.setEditable(true);
        tfIdade.setEditable(true);
        tfCpfCnpj.setEditable(true);
        tfTelefone.setEditable(true);
    }
    // deshabilita os textfields
    private void desabilitarCampos(){
        tfNome.setEditable(false);
        tfIdade.setEditable(false);
        tfCpfCnpj.setEditable(false);
        tfTelefone.setEditable(false);
    }
    // inserir o cliente
    private void inserir(){
        try {
            cl.setNomeCliente(tfNome.getText());
            cl.setIdadeCliente(Integer.valueOf(tfIdade.getText().trim()));
            cl.setCpfCnpjCliente(tfCpfCnpj.getText().trim());
            String fj = (String) cbFisicaJuridica.getSelectedItem();
            System.out.println("Pessoa: "+fj.charAt(7));
            cl.setFisicaJuridicaCliente(fj.charAt(7));
            cl.setTelefoneCliente(tfTelefone.getText().trim());
            dao.inserirCliente(cl);
            JOptionPane.showMessageDialog(null, "Gravado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de cliente para que a listagem dos clientes recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    // atualizar cliente
    private void update(){
        try {
            cl.setIdCliente(Long.valueOf(tfCodigo.getText().trim()));
            cl.setNomeCliente(tfNome.getText());
            cl.setIdadeCliente(Integer.valueOf(tfIdade.getText().trim()));
            cl.setCpfCnpjCliente(tfCpfCnpj.getText().trim());
            String fj = (String) cbFisicaJuridica.getSelectedItem();
            System.out.println("Pessoa: "+fj.charAt(7));
            cl.setFisicaJuridicaCliente(fj.charAt(7));
            cl.setTelefoneCliente(tfTelefone.getText().trim());
            dao.updateCliente(cl);
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de clientes para que a listagem dos clientes recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    // deletar cliente
    private void deletar(){
        try {
            cl.setIdCliente(Long.valueOf(tfCodigo.getText().trim()));
            dao.deleteCliente(cl);
            JOptionPane.showMessageDialog(null, "Deletado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de clientes para que a listagem dos clientes recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    // limpa os textfield
    private void limparCampos(){
        tfCodigo.setText("");
        tfNome.setText("");
        tfIdade.setText("");
        tfCpfCnpj.setText("");
        tfTelefone.setText("");
    }
    // gera um pdf da tabela de listagem
    private void gerarListaPdf() {
        new ImageIcon("./src/Img/crm.png");
        FileDialog fileChooser = new FileDialog(this, "Salvar Relatório de Clientes", java.awt.FileDialog.SAVE);

        fileChooser.setFile("clientes.pdf");
        
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
                lista = dao.listarCliente();
                PdfWriter.getInstance(documento, new FileOutputStream(arquivoParaSalvar));
                documento.open();
                
                LocalDate data = LocalDate.now();
                String dataHj = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                
                documento.add(imagemLogo);
                documento.addAuthor("Aleph Enzo Guimarães da Silva");
                documento.add(new Paragraph("Relatório de clientes:                     Data:" + dataHj));
                documento.add(new Paragraph(" "));

                PdfPTable tabela = new PdfPTable(6);
                tabela.setWidthPercentage(90);

                float[] larguras = {4f, 15f, 5f, 12f, 3f, 12f};
                tabela.setWidths(larguras);

                // Cabeçalho
                tabela.addCell(new PdfPCell(new Paragraph("Id")));
                tabela.addCell(new PdfPCell(new Paragraph("Nome")));
                tabela.addCell(new PdfPCell(new Paragraph("Idade")));
                tabela.addCell(new PdfPCell(new Paragraph("Cpf ou Cnpj")));
                tabela.addCell(new PdfPCell(new Paragraph("F ou J")));
                tabela.addCell(new PdfPCell(new Paragraph("Telefone")));

                for(clienteBeans cli : lista) {
                    tabela.addCell(String.valueOf(cli.getIdCliente()));
                    tabela.addCell(cli.getNomeCliente());
                    tabela.addCell(String.valueOf(cli.getIdadeCliente()));
                    tabela.addCell(cli.getCpfCnpjCliente());
                    tabela.addCell(String.valueOf(cli.getFisicaJuridicaCliente()));
                    tabela.addCell(cli.getTelefoneCliente());
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
                } else {
                    // documento já está fechado
                }
            }
        }
    }
}