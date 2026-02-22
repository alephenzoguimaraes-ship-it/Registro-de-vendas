package swing.FrmFuncionarios;

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

import Model.funcionario.funcionarioBeans;
import Model.funcionario.funcionarioDao;

public class funcionarios extends JFrame{
    private funcionarioBeans f = new funcionarioBeans();
    private funcionarioDao dao = new funcionarioDao();
    private ArrayList<funcionarioBeans> lista = new ArrayList<>();
    private Image imagemLogo;
    private ImageIcon iconeFuncionarios, iconeNovo, iconeSalvar, iconeEditar, iconeExcluir, iconeGerarRelatorio;
    private JTabbedPane panelPai;
    private JPanel panelCadastro, panelLista;
    private JScrollPane scrollTableListar;
    private JButton btNovo, btSalvar, btEditar, btExcluir, btGerarRelatorio;
    private JLabel lbCodigo, lbNome, lbIdade, lbCpf, lbTelefone, lbComissao, lbProfissao, lbSalario;
    private JTextField tfCodigo, tfNome, tfIdade, tfCpf, tfTelefone, tfComissao, tfProfissao, tfSalario;
    private JTable tbListaFuncionarios;
    public funcionarios() {
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();        
        this.setSize(tela.width, tela.height-60);
        iconeFuncionarios = new ImageIcon("./src/Img/funcionarioIcon.png");
        this.setIconImage(iconeFuncionarios.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Funcionários");

        // iniciando os icones
        iconeNovo = new ImageIcon("./src/Img/novo.png");
        iconeSalvar = new ImageIcon("./src/Img/salvar.png");
        iconeEditar = new ImageIcon("./src/Img/editar.png");
        iconeExcluir = new ImageIcon("./src/Img/excluir.png");
        iconeGerarRelatorio = new ImageIcon("./src/Img/Relatorio.png");
        
        // iniciando os butões
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
        btGerarRelatorio = new JButton("", iconeGerarRelatorio){
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
        btGerarRelatorio.setBackground(Color.decode("#292e39"));

        // setando as coordenadas dos botões
        btNovo.setBounds(10, 10, 36, 36);
        btSalvar.setBounds(66, 10, 36, 36);
        btEditar.setBounds(122, 10, 36, 36);
        btExcluir.setBounds(178, 10, 36, 36);
        btGerarRelatorio.setBounds(10, 10, 36, 36);

        // tirando as coisas dos botões para deixar eles redondos
        btNovo.setContentAreaFilled(false);       
        btSalvar.setContentAreaFilled(false);
        btEditar.setContentAreaFilled(false);
        btExcluir.setContentAreaFilled(false);
        btGerarRelatorio.setContentAreaFilled(false);
        
        btNovo.setFocusPainted(false);       
        btSalvar.setFocusPainted(false);
        btEditar.setFocusPainted(false);
        btExcluir.setFocusPainted(false);
        btGerarRelatorio.setFocusPainted(false);
        
        btNovo.setBorderPainted(false);       
        btSalvar.setBorderPainted(false);
        btEditar.setBorderPainted(false);
        btExcluir.setBorderPainted(false);
        btGerarRelatorio.setBorderPainted(false);

        // adicionando o hand curso no mouse
        btNovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));       
        btSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btGerarRelatorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // adicionando o tooltip nos botoes
        btNovo.setToolTipText("Novo");       
        btSalvar.setToolTipText("Salvar ou Atualizar");
        btEditar.setToolTipText("Editar");
        btExcluir.setToolTipText("Excluir");
        btGerarRelatorio.setToolTipText("Gerar relatório pdf");

        // adicionando as ações dos botoes
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
                        JOptionPane.showMessageDialog(null, "Clique no botão Novo para inserir,\n ou para atualiza clique na lista,\n depois clique no funcionário que deseja atualizar", "", JOptionPane.WARNING_MESSAGE);
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
                int verificar = JOptionPane.showConfirmDialog(null, "Para que você delete um funcionário é necessário primeiro deletar a venda!!\nDeseja continuar com o processo?", "", JOptionPane.YES_NO_OPTION);
                if(verificar == 0){
                    int escolha = JOptionPane.showConfirmDialog(null, "Deseja deletar este funcionário?", "", JOptionPane.YES_NO_OPTION);
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
        btGerarRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarListaPdf();
            }            
        });

        // iniciando os tf
        tfCodigo = new JTextField();
        tfNome = new JTextField();
        tfIdade = new JTextField();
        tfCpf = new JTextField();
        tfTelefone = new JTextField();
        tfComissao = new JTextField("0,00");
        tfProfissao = new JTextField();
        tfSalario = new JTextField("0,00");

        // setando coordenadas dos tf
        tfCodigo.setBounds(10, 90, 100, 25);
        tfNome.setBounds(10, 160, 250, 25);
        tfIdade.setBounds(10, 230, 50, 25);
        tfCpf.setBounds(10, 300, 100, 25);
        tfTelefone.setBounds(10, 370, 150, 25);
        tfComissao.setBounds(10, 440, 70, 25);
        tfProfissao.setBounds(10, 510, 150, 25);
        tfSalario.setBounds(10, 580, 70, 25);

        // setando o fundo dos tf
        tfCodigo.setBackground(Color.decode("#292e39"));
        tfNome.setBackground(Color.decode("#292e39"));
        tfIdade.setBackground(Color.decode("#292e39"));
        tfCpf.setBackground(Color.decode("#292e39"));
        tfTelefone.setBackground(Color.decode("#292e39"));
        tfComissao.setBackground(Color.decode("#292e39"));
        tfProfissao.setBackground(Color.decode("#292e39"));
        tfSalario.setBackground(Color.decode("#292e39"));

        // setando a cor da letra dos tf
        tfCodigo.setForeground(Color.decode("#88c0d0"));
        tfNome.setForeground(Color.decode("#88c0d0"));
        tfIdade.setForeground(Color.decode("#88c0d0"));
        tfCpf.setForeground(Color.decode("#88c0d0"));
        tfTelefone.setForeground(Color.decode("#88c0d0"));
        tfComissao.setForeground(Color.decode("#88c0d0"));
        tfProfissao.setForeground(Color.decode("#88c0d0"));
        tfSalario.setForeground(Color.decode("#88c0d0"));

        // setando a cor do Caret
        tfCodigo.setCaretColor(Color.decode("#88c0d0"));
        tfNome.setCaretColor(Color.decode("#88c0d0"));
        tfIdade.setCaretColor(Color.decode("#88c0d0"));
        tfCpf.setCaretColor(Color.decode("#88c0d0"));
        tfTelefone.setCaretColor(Color.decode("#88c0d0"));
        tfComissao.setCaretColor(Color.decode("#88c0d0"));
        tfProfissao.setCaretColor(Color.decode("#88c0d0"));
        tfSalario.setCaretColor(Color.decode("#88c0d0"));

        tfCodigo.setBorder(null);
        tfNome.setBorder(null);
        tfIdade.setBorder(null);
        tfCpf.setBorder(null);
        tfTelefone.setBorder(null);
        tfComissao.setBorder(null);
        tfProfissao.setBorder(null);
        tfSalario.setBorder(null);

        // tirando editable
        tfCodigo.setEditable(false);
        tfNome.setEditable(false);
        tfIdade.setEditable(false);
        tfCpf.setEditable(false);
        tfTelefone.setEditable(false);
        tfComissao.setEditable(false);
        tfProfissao.setEditable(false);
        tfSalario.setEditable(false);

        // iniciando os lbs
        lbCodigo = new JLabel("Id.:");
        lbNome = new JLabel("Nome.:");
        lbIdade = new JLabel("Idade.:");
        lbCpf = new JLabel("Cpf.:");
        lbTelefone = new JLabel("Telefone.:");
        lbComissao = new JLabel("Comissão.:");
        lbProfissao = new JLabel("Profissão.:");
        lbSalario = new JLabel("Salário.:");

        // setando a coordenadas dos lbs
        lbCodigo.setBounds(10, 70, 30, 25);
        lbNome.setBounds(10, 140, 50, 25);
        lbIdade.setBounds(10, 210, 50, 25);
        lbCpf.setBounds(10, 280, 30, 25);
        lbTelefone.setBounds(10, 350, 70, 25);
        lbComissao.setBounds(10, 420, 70, 25);
        lbProfissao.setBounds(10, 490, 70, 25);
        lbSalario.setBounds(10, 560, 70, 25);

        // setando as cores do lbs
        lbCodigo.setForeground(Color.decode("#ffffff"));
        lbNome.setForeground(Color.decode("#ffffff"));
        lbIdade.setForeground(Color.decode("#ffffff"));
        lbCpf.setForeground(Color.decode("#ffffff"));
        lbTelefone.setForeground(Color.decode("#ffffff"));
        lbComissao.setForeground(Color.decode("#ffffff"));
        lbProfissao.setForeground(Color.decode("#ffffff"));
        lbSalario.setForeground(Color.decode("#ffffff"));

        // iniciando o panel pai
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

        // iniciando o cadastro
        panelCadastro = new JPanel();
        panelCadastro.setBackground(Color.decode("#382b73"));
        panelCadastro.setLayout(null);
        
        panelCadastro.add(btNovo);
        panelCadastro.add(btSalvar);
        panelCadastro.add(btEditar);
        panelCadastro.add(btExcluir);

        panelCadastro.add(tfCodigo);
        panelCadastro.add(tfNome);
        panelCadastro.add(tfIdade);
        panelCadastro.add(tfCpf);
        panelCadastro.add(tfTelefone);
        panelCadastro.add(tfComissao);
        panelCadastro.add(tfProfissao);
        panelCadastro.add(tfSalario);

        panelCadastro.add(lbCodigo);
        panelCadastro.add(lbNome);
        panelCadastro.add(lbIdade);
        panelCadastro.add(lbCpf);
        panelCadastro.add(lbTelefone);
        panelCadastro.add(lbComissao);
        panelCadastro.add(lbProfissao);
        panelCadastro.add(lbSalario);

        // iniciando o lista
        panelLista = new JPanel();
        panelLista.setBackground(Color.decode("#382b73"));
        panelLista.setLayout(null);

        // criando tabela de funcionarios
        String[] colunas = {"Id", "Nome", "Idade", "Cpf", "Telefone", "Comissão", "Profissão", "Salário"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        lista = dao.listaFuncionario();
        for (funcionarioBeans f : lista) {
            String comissao = String.valueOf(f.getComissaoFuncionario()).replace(".", ",");
            String salario = String.valueOf(f.getSalarioFuncionario()).replace(".", ",");
            Object[] linha = {
                f.getIdFuncionario(),
                f.getNomeFuncionario(),
                f.getIdadeFuncionario(),
                f.getCpfFuncionario(),
                f.getTelefoneFuncionario(),
                comissao,
                f.getProfissaoFuncionario(),
                salario
            };
            modelo.addRow(linha);
        }
        tbListaFuncionarios = new JTable(modelo);
        tbListaFuncionarios.setBackground(Color.decode("#292e39"));
        tbListaFuncionarios.setForeground(Color.decode("#88c0d0"));
        tbListaFuncionarios.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                int linha = tbListaFuncionarios.getSelectedRow();
                if (linha == -1) return;
                    int linhaModelo = tbListaFuncionarios.convertRowIndexToModel(linha);
                    tfCodigo.setText(tbListaFuncionarios.getModel().getValueAt(linhaModelo, 0).toString());
                    tfNome.setText(tbListaFuncionarios.getModel().getValueAt(linhaModelo, 1).toString());
                    tfIdade.setText(tbListaFuncionarios.getModel().getValueAt(linhaModelo, 2).toString());
                    tfCpf.setText(tbListaFuncionarios.getModel().getValueAt(linhaModelo, 3).toString());
                    tfTelefone.setText(tbListaFuncionarios.getModel().getValueAt(linhaModelo, 4).toString());
                    tfComissao.setText(tbListaFuncionarios.getModel().getValueAt(linhaModelo, 5).toString());
                    tfProfissao.setText(tbListaFuncionarios.getModel().getValueAt(linhaModelo, 6).toString());
                    tfSalario.setText(tbListaFuncionarios.getModel().getValueAt(linhaModelo, 7).toString());

                    panelPai.setSelectedComponent(panelCadastro);
                
            }
        });
        
        // colocando o scrollpane na tabela
        scrollTableListar = new JScrollPane(tbListaFuncionarios);
        scrollTableListar.setBounds(0, 100, tela.width, 700);
        scrollTableListar.getViewport().setOpaque(false);
        scrollTableListar.setBorder(BorderFactory.createEmptyBorder());
        scrollTableListar.setOpaque(false);
        panelLista.add(scrollTableListar);
        panelLista.add(btGerarRelatorio);
        
        panelPai.add(panelCadastro, "Cadastro");
        panelPai.add(panelLista, "Lista");
        this.add(panelPai);
        this.setVisible(true);
    }
    // inserir funcionário
    private void inserir() {
        try {
            f.setNomeFuncionario(tfNome.getText());
            f.setIdadeFuncionario(Integer.valueOf(tfIdade.getText().trim()));
            f.setCpfFuncionario(tfCpf.getText().trim());
            f.setTelefoneFuncionario(tfTelefone.getText().trim());
            f.setComissaoFuncionario(Double.valueOf(tfComissao.getText().trim().replace(",", ".")));
            f.setProfissaoFuncionario(tfProfissao.getText());
            f.setSalarioFuncionario(Double.valueOf(tfSalario.getText().trim().replace(",", ".")));
            dao.inserirFuncionario(f);
            JOptionPane.showMessageDialog(null, "Gravado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de funcionários para que a listagem dos funcionários recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    // update funcionário
    private void update() {
        try {
            f.setIdFuncionario(Long.valueOf(tfCodigo.getText().trim()));
            f.setNomeFuncionario(tfNome.getText());
            f.setIdadeFuncionario(Integer.valueOf(tfIdade.getText().trim()));
            f.setCpfFuncionario(tfCpf.getText().trim());
            f.setTelefoneFuncionario(tfTelefone.getText().trim());
            f.setComissaoFuncionario(Double.valueOf(tfComissao.getText().trim().replace(",", ".")));
            f.setProfissaoFuncionario(tfProfissao.getText());
            f.setSalarioFuncionario(Double.valueOf(tfSalario.getText().trim().replace(",", ".")));
            dao.updateFuncionario(f);
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de funcionários para que a listagem dos funcionários recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    private void deletar() {
        try {
            f.setIdFuncionario(Long.valueOf(tfCodigo.getText().trim()));
            dao.deleteFuncionario(f);
            JOptionPane.showMessageDialog(null, "Deletado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de funcionários para que a listagem dos funcionários recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    // habilitar tf
    private void habilitarCampos() {
        tfNome.setEditable(true);
        tfIdade.setEditable(true);
        tfCpf.setEditable(true);
        tfTelefone.setEditable(true);
        tfComissao.setEditable(true);
        tfProfissao.setEditable(true);
        tfSalario.setEditable(true);
    }
    // deshabilitar tf
    private void desabilitarCampos() {
        tfNome.setEditable(false);
        tfIdade.setEditable(false);
        tfCpf.setEditable(false);
        tfTelefone.setEditable(false);
        tfComissao.setEditable(false);
        tfProfissao.setEditable(false);
        tfSalario.setEditable(false);
    }
    // limpa os tf
    private void limparCampos() {
        tfCodigo.setText("");
        tfNome.setText("");
        tfIdade.setText("");
        tfCpf.setText("");
        tfTelefone.setText("");
        tfComissao.setText("0,00");
        tfProfissao.setText("");
        tfSalario.setText("0,00");
    }
    // gera um pdf de funcionários
    private void gerarListaPdf() {
        new ImageIcon("./src/Img/crm.png");
        FileDialog fileChooser = new FileDialog(this, "Salvar Relatório de Funcionários", java.awt.FileDialog.SAVE);

        fileChooser.setFile("funcionarios.pdf");
        
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
                lista = dao.listaFuncionario();
                PdfWriter.getInstance(documento, new FileOutputStream(arquivoParaSalvar));
                documento.open();
                
                LocalDate data = LocalDate.now();
                String dataHj = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                
                documento.add(imagemLogo);
                documento.addAuthor("Aleph Enzo Guimarães da Silva");
                documento.add(new Paragraph("Relatório de funcionários:                     Data:" + dataHj));
                documento.add(new Paragraph(" "));

                PdfPTable tabela = new PdfPTable(8);
                tabela.setWidthPercentage(90);

                float[] larguras = {4f, 15f, 5f, 12f, 12f, 6f, 10f, 7f};
                tabela.setWidths(larguras);

                // Cabeçalho
                tabela.addCell(new PdfPCell(new Paragraph("Id")));
                tabela.addCell(new PdfPCell(new Paragraph("Nome")));
                tabela.addCell(new PdfPCell(new Paragraph("Idade")));
                tabela.addCell(new PdfPCell(new Paragraph("Cpf")));
                tabela.addCell(new PdfPCell(new Paragraph("Telefone")));
                tabela.addCell(new PdfPCell(new Paragraph("Comissão")));
                tabela.addCell(new PdfPCell(new Paragraph("Profissão")));
                tabela.addCell(new PdfPCell(new Paragraph("Salário")));

                for(funcionarioBeans fun : lista) {
                    tabela.addCell(String.valueOf(fun.getIdFuncionario()));
                    tabela.addCell(fun.getNomeFuncionario());
                    tabela.addCell(String.valueOf(fun.getIdadeFuncionario()));
                    tabela.addCell(fun.getCpfFuncionario());
                    tabela.addCell(fun.getTelefoneFuncionario());
                    tabela.addCell("R$ "+String.valueOf(fun.getComissaoFuncionario()).replace(".", ","));
                    tabela.addCell(fun.getProfissaoFuncionario());
                    tabela.addCell("R$ "+String.valueOf(fun.getSalarioFuncionario()).replace(".", ","));
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
