package br.com.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.dao.CRUDChamado;
import br.com.dominio.Chamado;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;

public class SistemaSuporte extends JFrame {
	private JTextField txtNome;
	private JTextField txtDepartamento;
	private JFormattedTextField txtDataAbertura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SistemaSuporte frame = new SistemaSuporte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SistemaSuporte() {
		setBounds(100, 100, 543, 447);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Insira seu nome:");
		lblNewLabel.setBounds(210, 46, 145, 14);
		getContentPane().add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setBounds(210, 73, 242, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Informe com qual departamento deseja falar:");
		lblNewLabel_1.setBounds(210, 140, 266, 14);
		getContentPane().add(lblNewLabel_1);

		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(210, 165, 242, 20);
		getContentPane().add(txtDepartamento);
		txtDepartamento.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Conte-nos mais sobre seu problema:");
		lblNewLabel_2.setBounds(210, 220, 266, 14);
		getContentPane().add(lblNewLabel_2);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(210, 251, 289, 130);
		getContentPane().add(panel);
		panel.setLayout(null);

		JTextArea txtDescricao = new JTextArea();
		txtDescricao.setBorder(new LineBorder(UIManager.getColor("Button.darkShadow")));
		txtDescricao.setBounds(0, 0, 289, 130);
		panel.add(txtDescricao);

		JButton btnsoliChamado = new JButton("Registrar Chamado");
		btnsoliChamado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Chamado soliChamado = new Chamado();
				CRUDChamado cc = new CRUDChamado();

				if (txtNome.getText().trim().equals("") || txtDepartamento.getText().trim().equals("")
						|| txtDescricao.getText().trim().equals("") || txtDataAbertura.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro 4000765x",
							JOptionPane.ERROR_MESSAGE);
				} else {

					soliChamado.setDataAbertura(txtDataAbertura.getText());

					soliChamado.setSolicitacao(txtNome.getText());

					soliChamado.setDepSolicitado(txtDepartamento.getText());

					soliChamado.setDescChamado(txtDescricao.getText());

					JOptionPane.showMessageDialog(null, cc.registrar(soliChamado));

				}
			}
		});
		btnsoliChamado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnsoliChamado.setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 2));
		btnsoliChamado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnsoliChamado.setContentAreaFilled(false);
		btnsoliChamado.setBounds(10, 164, 153, 23);
		getContentPane().add(btnsoliChamado);

		JLabel lblNewLabel_3 = new JLabel("Data de Abertura");
		lblNewLabel_3.setBounds(10, 287, 100, 14);
		getContentPane().add(lblNewLabel_3);

		MaskFormatter msf = null;
		try {
			msf = new MaskFormatter("##/##/####");
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtDataAbertura = new JFormattedTextField(msf);
		txtDataAbertura.setBounds(10, 309, 76, 20);
		getContentPane().add(txtDataAbertura);

	}// fim do construtor

}// fim do codigo