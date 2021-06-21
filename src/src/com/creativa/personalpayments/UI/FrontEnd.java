package com.creativa.personalpayments.UI;

import com.creativa.personalpayments.repositories.FileRepository;
import com.creativa.personalpayments.services.PPService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FrontEnd extends JFrame {

    public FrontEnd(String titulo){
        super(titulo);
    }

    public FrontEnd(){

    }

    public void build(){
        this.construccionPantalla();
        this.crearComponentes();
        super.setVisible(true);
    }

    private void construccionPantalla(){
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(800,400);
        super.setLayout(new GridLayout(7,1));
    }

    private void agregarComponente(Component componente){
        super.getContentPane().add(componente);
    }

    private void crearComponentes(){
        //Create Panels
        JPanel pregunta1 = new JPanel();
        pregunta1.setLayout(new FlowLayout());
        JPanel pregunta2 = new JPanel();
        pregunta2.setLayout(new GridLayout(1,2));
        JPanel pregunta3 = new JPanel();
        pregunta3.setLayout(new FlowLayout());
        JPanel pregunta4 = new JPanel();
        pregunta4.setLayout(new FlowLayout());
        JPanel pregunta5 = new JPanel();
        pregunta5.setLayout(new GridLayout(1,2));
        JPanel pregunta6 = new JPanel();
        pregunta6.setLayout(new GridLayout(1,2));

        //Create Labels
        JLabel lblPaymentType = new JLabel("Tipo de pago");
        JLabel lblNombre = new JLabel("Nombre del pago");
        JLabel lblServiceType = new JLabel("Servicio");
        JLabel lblCreditCard = new JLabel("Tipo de tarjeta");
        JLabel lblAmount = new JLabel("Monto aproximado del pago");
        JLabel lblPaymentDate = new JLabel("Dia de pago del mes");

        //Create Fields
        JTextField txtNombre = new JTextField();

        String[] pservices = new String[] {"Seleccionar","CNFL", "Internet", "Celular", "AyA", "Municipalidad"};
        JComboBox<String> stringJComboBoxServiceType = new JComboBox<String>(pservices);
        stringJComboBoxServiceType.setSelectedItem("Seleccionar");

        JRadioButton jrbCreditCard1 = new JRadioButton("Visa");
        JRadioButton jrbCreditCard2 = new JRadioButton("AMEX");
        ButtonGroup bgTipoCc = new ButtonGroup();
        bgTipoCc.add(jrbCreditCard1);
        bgTipoCc.add(jrbCreditCard2);

        JTextField txtAmount = new JTextField();
        JTextField txtPaymentDate = new JTextField();

        JRadioButton jRadioButtonspt = new JRadioButton("Tarjeta de credito");
        JRadioButton jrbservicio = new JRadioButton("Servicio");
        ButtonGroup bgTipoPago = new ButtonGroup();
        bgTipoPago.add(jRadioButtonspt);
        bgTipoPago.add(jrbservicio);
        jRadioButtonspt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblCreditCard.setVisible(true);
                jrbCreditCard1.setVisible(true);
                jrbCreditCard2.setVisible(true);
                lblServiceType.setVisible(false);
                stringJComboBoxServiceType.setVisible(false);
                stringJComboBoxServiceType.setSelectedItem("Seleccionar");
            }
        });
        jrbservicio.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblCreditCard.setVisible(false);
                jrbCreditCard1.setVisible(false);
                jrbCreditCard2.setVisible(false);
                jrbCreditCard1.setSelected(false);
                jrbCreditCard2.setSelected(false);
                lblServiceType.setVisible(true);
                stringJComboBoxServiceType.setVisible(true);
            }
        });

        JButton guardar = new JButton("Guardar");
        guardar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PPService service = new PPService(new FileRepository());
                boolean validado = true;
                String tipoPago = "";
                String tipoCC = "";
                if(jRadioButtonspt.isSelected() || jrbservicio.isSelected()) {
                    if (jRadioButtonspt.isSelected()) {
                        tipoPago = "Tarjeta de credito";
                    } else {
                        tipoPago = "Servicio";
                    }
                }else{
                    validado=false;
                }
                if(txtNombre.getText().isEmpty() || txtNombre.getText().isBlank()){
                    validado=false;
                }
                if (stringJComboBoxServiceType.getSelectedIndex()==0 && jrbservicio.isSelected()){
                    validado=false;
                }
                System.out.println(stringJComboBoxServiceType.getSelectedIndex());
                System.out.println(jrbservicio.isSelected());
                if((jrbCreditCard1.isSelected() || jrbCreditCard2.isSelected()) && jRadioButtonspt.isSelected()){
                    if (jrbCreditCard1.isSelected()) {
                        tipoCC = "Visa";
                    } else {
                        tipoCC = "AMEX";
                    }
                }else{
                    if(!jrbservicio.isSelected()){
                        validado=false;
                    }

                }

                if(txtAmount.getText().isEmpty() || txtAmount.getText().isBlank()){
                    validado=false;
                }
                if(txtPaymentDate.getText().isEmpty() || txtPaymentDate.getText().isBlank()){
                    validado=false;
                }
                System.out.println(validado);
                if(validado) {
                    service.save(tipoPago,
                            txtNombre.getText(),
                            (String) stringJComboBoxServiceType.getSelectedItem(),
                            tipoCC,
                            txtAmount.getText(),
                            txtPaymentDate.getText());

                    txtNombre.setText("");
                    jRadioButtonspt.setSelected(false);
                    jrbservicio.setSelected(false);
                    stringJComboBoxServiceType.setSelectedItem("");
                    jrbCreditCard1.setSelected(false);
                    jrbCreditCard2.setSelected(false);
                    txtAmount.setText("");
                    txtPaymentDate.setText("");

                    String summary = String.join("\n", service.get());
                    JOptionPane.showMessageDialog(((JButton) e.getSource()).getParent(), summary);
                }else{
                    JOptionPane.showMessageDialog(null,"Debe llenar toda la informacion.", "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Add to UI
        pregunta1.add(lblPaymentType);
        pregunta1.add(jRadioButtonspt);
        pregunta1.add(jrbservicio);
        this.agregarComponente(pregunta1);


        pregunta2.add(lblNombre);
        pregunta2.add(txtNombre);
        this.agregarComponente(pregunta2);

        pregunta3.add(lblServiceType);
        pregunta3.add(stringJComboBoxServiceType);
        this.agregarComponente(pregunta3);


        pregunta4.add(lblCreditCard);
        pregunta4.add(jrbCreditCard1);
        pregunta4.add(jrbCreditCard2);
        this.agregarComponente(pregunta4);

        pregunta5.add(lblAmount);
        pregunta5.add(txtAmount);
        this.agregarComponente(pregunta5);

        pregunta6.add(lblPaymentDate);
        pregunta6.add(txtPaymentDate);
        this.agregarComponente(pregunta6);

        this.agregarComponente(guardar);

        lblCreditCard.setVisible(false);
        jrbCreditCard1.setVisible(false);
        jrbCreditCard2.setVisible(false);
        lblServiceType.setVisible(false);
        stringJComboBoxServiceType.setVisible(false);

    }
}
