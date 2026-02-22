package swing.FrmProdutos;

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
import javax.swing.JTextArea;
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

import Model.produto.produtoBeans;
import Model.produto.produtoDao;

public class produtos extends JFrame{
    private produtoBeans pr = new produtoBeans();
    private produtoDao dao = new produtoDao();
    private ArrayList<produtoBeans> lista = new ArrayList<produtoBeans>();
    private Image imagemLogo;
    private ImageIcon iconeProdutos, iconeNovo, iconeSalvar, iconeEditar, iconeExcluir, iconeGerarRelatorio;
    private JTabbedPane panelPai;
    private JPanel panelCadastro, panelLista;
    private JTable tbListaProdutos;
    private JScrollPane scrollPanelLista;
    private JButton btNovo, btSalvar, btEditar, btExcluir, btGerarRelatorio;
    private JLabel lbCodigo, lbNome, lbDescricao, lbQtde, lbValor;
    private JTextField tfCodigo, tfNome, tfQtde, tfValor;
    private JTextArea tfDescricao;
    public produtos() {
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(tela.width, tela.height-60);
        this.setLocationRelativeTo(null);
        iconeProdutos = new ImageIcon("./src/Img/produtosIcon.png");
        this.setIconImage(iconeProdutos.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Produtos");

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
                        JOptionPane.showMessageDialog(null, "Clique no botão Novo para inserir,\n ou para atualiza clique na lista,\n depois clique no produto que deseja atualizar", "", JOptionPane.WARNING_MESSAGE);
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
                int verificar = JOptionPane.showConfirmDialog(null, "Para que você delete um produto é necessário primeiro deletar a venda!!\nDeseja continuar com o processo?", "", JOptionPane.YES_NO_OPTION);
                if(verificar == 0) {
                    int escolha = JOptionPane.showConfirmDialog(null, "Deseja deletar este produto?", "", JOptionPane.YES_NO_OPTION);
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

        // iniciando os lbs
        lbCodigo = new JLabel("Id.:");
        lbNome = new JLabel("Nome.:");
        lbDescricao = new JLabel("Descrição.:");
        lbQtde = new JLabel("Quantidade.:");
        lbValor = new JLabel("Valor.:");

        // setando as cores dos lbs
        lbCodigo.setForeground(Color.decode("#ffffff"));
        lbCodigo.setBounds(10, 70, 20, 25);

        lbNome.setForeground(Color.decode("#ffffff"));
        lbNome.setBounds(10, 140, 40, 25);

        lbDescricao.setForeground(Color.decode("#ffffff"));
        lbDescricao.setBounds(400, 70, 80, 25);

        lbQtde.setForeground(Color.decode("#ffffff"));
        lbQtde.setBounds(10, 210, 100, 25);

        lbValor.setForeground(Color.decode("#ffffff"));
        lbValor.setBounds(10, 280, 100, 25);

        // iniciando os tf
        tfCodigo = new JTextField();
        tfNome = new JTextField();
        tfDescricao = new JTextArea();
        tfQtde = new JTextField();
        tfValor = new JTextField("0,00");

        // deixando ele sem permissão de escrita para que dps o usuário possa editar clicando no botão novo
        tfCodigo.setEditable(false);
        tfNome.setEditable(false);
        tfDescricao.setEditable(false);
        tfQtde.setEditable(false);
        tfValor.setEditable(false);

        // setando as cores dos tfs as cores das letras e do caret e colocando as coordenadas
        tfCodigo.setBackground(Color.decode("#292e39"));
        tfCodigo.setForeground(Color.decode("#88c0d0"));
        tfCodigo.setCaretColor(Color.decode("#88c0d0"));
        tfCodigo.setBorder(null);
        tfCodigo.setBounds(10, 90, 80, 25);
        
        tfNome.setBackground(Color.decode("#292e39"));
        tfNome.setForeground(Color.decode("#88c0d0"));
        tfNome.setCaretColor(Color.decode("#88c0d0"));
        tfNome.setBorder(null);
        tfNome.setBounds(10, 160, 250, 25);
        
        tfDescricao.setBackground(Color.decode("#292e39"));
        tfDescricao.setForeground(Color.decode("#88c0d0"));
        tfDescricao.setCaretColor(Color.decode("#88c0d0"));
        tfDescricao.setBorder(null);
        tfDescricao.setBounds(400, 90, 300, 120);
        
        tfQtde.setBackground(Color.decode("#292e39"));
        tfQtde.setForeground(Color.decode("#88c0d0"));
        tfQtde.setCaretColor(Color.decode("#88c0d0"));
        tfQtde.setBorder(null);
        tfQtde.setBounds(10, 230, 120, 25);
        
        tfValor.setBackground(Color.decode("#292e39"));
        tfValor.setForeground(Color.decode("#88c0d0"));
        tfValor.setCaretColor(Color.decode("#88c0d0"));
        tfValor.setBorder(null);
        tfValor.setBounds(10, 300, 120, 25);

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

        panelCadastro = new JPanel();
        panelCadastro.setLayout(null);
        panelCadastro.setBackground(Color.decode("#382b73"));
        panelCadastro.add(btNovo);
        panelCadastro.add(btSalvar);
        panelCadastro.add(btEditar);
        panelCadastro.add(btExcluir);
        panelCadastro.add(lbCodigo);
        panelCadastro.add(tfCodigo);
        panelCadastro.add(lbNome);
        panelCadastro.add(tfNome);
        panelCadastro.add(lbDescricao);
        panelCadastro.add(tfDescricao);
        panelCadastro.add(lbQtde);
        panelCadastro.add(tfQtde);
        panelCadastro.add(lbValor);
        panelCadastro.add(tfValor);

        panelLista = new JPanel();
        panelLista.setLayout(null);
        panelLista.setBackground(Color.decode("#382b73"));

        // Criando a lista no produtos
        String[] colunas = {"Id", "Nome", "Descrição", "Quantidade", "Valor"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        lista = dao.listarProduto();
        for(produtoBeans prod : lista) {
            String valor = String.valueOf(prod.getValorProduto()).replace(".", ",");
            Object[] linha = {
                prod.getIdProduto(),
                prod.getNomeProduto(),
                prod.getDescricaoProduto(),
                prod.getQtdeProduto(),
                valor
            };
            modelo.addRow(linha);
        }

        tbListaProdutos = new JTable(modelo);
        tbListaProdutos.setBackground(Color.decode("#292e39"));
        tbListaProdutos.setForeground(Color.decode("#88c0d0"));
        tbListaProdutos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int linha = tbListaProdutos.getSelectedRow();
                if (linha == -1) return;
                int linhaModelo = tbListaProdutos.convertRowIndexToModel(linha);
                tfCodigo.setText(tbListaProdutos.getModel().getValueAt(linhaModelo, 0).toString());
                tfNome.setText(tbListaProdutos.getModel().getValueAt(linhaModelo, 1).toString());
                tfDescricao.setText(tbListaProdutos.getModel().getValueAt(linhaModelo, 2).toString());
                tfQtde.setText(tbListaProdutos.getModel().getValueAt(linhaModelo, 3).toString());
                tfValor.setText(tbListaProdutos.getModel().getValueAt(linhaModelo, 4).toString());

                panelPai.setSelectedComponent(panelCadastro);
            }
        });

        // colocando tudo isso no scrollpanel
        scrollPanelLista = new JScrollPane(tbListaProdutos);
        scrollPanelLista.setBounds(0, 100, tela.width, 700);
        scrollPanelLista.getViewport().setOpaque(false);
        scrollPanelLista.setBorder(BorderFactory.createEmptyBorder());
        panelLista.add(scrollPanelLista);
        panelLista.add(btGerarRelatorio);

        panelPai.add(panelCadastro, "Cadastro");
        panelPai.add(panelLista, "Lista");
        this.add(panelPai);
        this.setVisible(true);
    }
    // habitita os campos
    private void habilitarCampos(){
        tfNome.setEditable(true);
        tfDescricao.setEditable(true);
        tfQtde.setEditable(true);
        tfValor.setEditable(true);
    }
    // desabilita os campos
    private void desabilitarCampos(){
        tfNome.setEditable(false);
        tfDescricao.setEditable(false);
        tfQtde.setEditable(false);
        tfValor.setEditable(false);
    }
    // inserir em produtos
    private void inserir(){
        try {
            pr.setNomeProduto(tfNome.getText());
            pr.setDescricaoProduto(tfDescricao.getText());
            pr.setQtdeProduto(Long.valueOf(tfQtde.getText().trim()));
            pr.setValorProduto(Double.valueOf(tfValor.getText().replace(",", ".")));
            dao.inserirProduto(pr);
            JOptionPane.showMessageDialog(null, "Gravado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de produtos para que a listagem dos produtos recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    // atualiza os produtos
    private void update(){
        try {
            pr.setIdProduto(Long.valueOf(tfCodigo.getText().trim()));
            pr.setNomeProduto(tfNome.getText());
            pr.setDescricaoProduto(tfDescricao.getText());
            pr.setQtdeProduto(Long.valueOf(tfQtde.getText().trim()));
            pr.setValorProduto(Double.valueOf(tfValor.getText().replace(",", ".")));
            dao.updateProduto(pr);
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de produtos para que a listagem dos produtos recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    // deleta os produtos
    private void deletar(){
        try {
            pr.setIdProduto(Long.valueOf(tfCodigo.getText().trim()));
            dao.deletarProduto(pr);
            JOptionPane.showMessageDialog(null, "Deletado com sucesso");
            JOptionPane.showMessageDialog(null, "Iremos fechar a tela de produtos para que a listagem dos produtos recarrege, beleza", "Atenção", JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no "+e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    private void limparCampos(){
        tfCodigo.setText("");
        tfNome.setText("");
        tfDescricao.setText("");
        tfQtde.setText("");
        tfValor.setText("");
    }
    // gera um pdf de produtos
    private void gerarListaPdf() {
        new ImageIcon("./src/Img/crm.png");
        FileDialog fileChooser = new FileDialog(this, "Salvar Relatório de Produtos", java.awt.FileDialog.SAVE);

        fileChooser.setFile("produtos.pdf");
        
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
                lista = dao.listarProduto();
                PdfWriter.getInstance(documento, new FileOutputStream(arquivoParaSalvar));
                documento.open();
                
                LocalDate data = LocalDate.now();
                String dataHj = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                
                documento.add(imagemLogo);
                documento.addAuthor("Aleph Enzo Guimarães da Silva");
                documento.add(new Paragraph("Relatório de produtos:                     Data:" + dataHj));
                documento.add(new Paragraph(" "));

                PdfPTable tabela = new PdfPTable(5);
                tabela.setWidthPercentage(90);

                float[] larguras = {4f, 10f, 20f, 6f, 6f};
                tabela.setWidths(larguras);

                // Cabeçalho
                tabela.addCell(new PdfPCell(new Paragraph("Id")));
                tabela.addCell(new PdfPCell(new Paragraph("Nome")));
                tabela.addCell(new PdfPCell(new Paragraph("Descrição")));
                tabela.addCell(new PdfPCell(new Paragraph("Quantidade")));
                tabela.addCell(new PdfPCell(new Paragraph("Valor")));

                for(produtoBeans p : lista) {
                    tabela.addCell(String.valueOf(p.getIdProduto()));
                    tabela.addCell(p.getNomeProduto());
                    tabela.addCell(p.getDescricaoProduto());
                    tabela.addCell(String.valueOf(p.getQtdeProduto()));
                    tabela.addCell("R$ "+String.valueOf(p.getValorProduto()).replace(".", ","));
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
