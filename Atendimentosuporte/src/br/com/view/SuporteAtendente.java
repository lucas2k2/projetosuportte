package br.com.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.dao.CRUDChamado;
import br.com.dominio.Chamado;

public class SuporteAtendente extends JFrame {
	private JTextField txtID;
	private JTextField txtResponsavel;
	private JFormattedTextField txtDataResolucao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuporteAtendente frame = new SuporteAtendente();
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
	public SuporteAtendente() {
		setBounds(100, 100, 623, 465);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID do Chamado");
		lblNewLabel.setBounds(240, 49, 85, 14);
		getContentPane().add(lblNewLabel);

		txtID = new JTextField();
		txtID.setBounds(201, 74, 155, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Status Chamado");
		lblNewLabel_1.setBounds(424, 49, 156, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Data de Resolução do Chamado");
		lblNewLabel_2.setBounds(191, 126, 192, 14);
		getContentPane().add(lblNewLabel_2);

		txtResponsavel = new JTextField();
		txtResponsavel.setColumns(10);
		txtResponsavel.setBounds(389, 164, 162, 20);
		getContentPane().add(txtResponsavel);

		JLabel lblNewLabel_3 = new JLabel("Responsável do Chamado");
		lblNewLabel_3.setBounds(399, 126, 152, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Observações");
		lblNewLabel_4.setBounds(331, 235, 78, 14);
		getContentPane().add(lblNewLabel_4);

		JTextArea txtObs = new JTextArea();
		txtObs.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtObs.setBounds(201, 260, 350, 155);
		getContentPane().add(txtObs);
		
		JComboBox cboStatus = new JComboBox();
		cboStatus.setModel(new DefaultComboBoxModel(new String[] { "Pendente", "Aberto", "Fechado" }));
		cboStatus.setBounds(389, 73, 162, 22);
		getContentPane().add(cboStatus);

		JButton btnAtualizarChamados = new JButton("Atualizar Chamados");
		btnAtualizarChamados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Chamado cr = new Chamado();
				CRUDChamado cc = new CRUDChamado();

				if (txtResponsavel.getText().trim().equals("") || txtID.getText().trim().equals("")
						|| txtDataResolucao.getText().trim().equals("") || cboStatus.getSelectedItem().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Os campos Responsável Chamado, Id do Chamado, Status do Chamado e Data de Resolução devem ser preenchidos",
							"Erro 4000765x", JOptionPane.ERROR_MESSAGE);
				} else {

					cr.setDataResolucao(txtDataResolucao.getText());
					cr.setStatusChamado(cboStatus.getSelectedItem().toString());
					cr.setAtendente(txtResponsavel.getText());
					cr.setObservacoes(txtObs.getText());
					cr.setIdChamado(Long.parseLong(txtID.getText()));
					cc.atualizar(cr);
					JOptionPane.showMessageDialog(null, "Chamado Atualizado");
				}

			}
		});
		btnAtualizarChamados.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAtualizarChamados.setContentAreaFilled(false);
		btnAtualizarChamados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtualizarChamados.setBounds(32, 163, 127, 23);
		getContentPane().add(btnAtualizarChamados);
		
		

		JButton btnExcluirChamados = new JButton("Excluir Chamados");
		btnExcluirChamados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CRUDChamado cc = new CRUDChamado();

				if (txtID.equals(null)) {
					JOptionPane.showMessageDialog(null, "Selecione o chamado a ser excluído.", "Erro 4000770x",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (JOptionPane.showConfirmDialog(null,
							"Você tem certeza desta ação? \nEstá ação é  permanente " + "e não pode ser desfeita",
							"ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {

						Chamado cr = new Chamado();
						cr.setIdChamado(Long.parseLong(txtID.getText()));
						JOptionPane.showMessageDialog(null, cc.apagar(cr));
					}
				}
			}
		});

		btnExcluirChamados.setContentAreaFilled(false);
		btnExcluirChamados.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnExcluirChamados.setBounds(32, 207, 127, 23);
		getContentPane().add(btnExcluirChamados);

		

		// metodo para marcar o formato do texto
		MaskFormatter msf = null;
		try {
			msf = new MaskFormatter("##/##/####");
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtDataResolucao = new JFormattedTextField(msf);
		txtDataResolucao.setBounds(201, 164, 155, 20);
		getContentPane().add(txtDataResolucao);

	}// fim do construtor

}// fim do codigo