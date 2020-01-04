
package kr.ac.itc.cms.cse.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import kr.ac.itc.cms.cse.oracle.Oracle_DAO;

@SuppressWarnings("serial")
public class Frame_NewAccount extends JFrame implements ActionListener, ItemListener, FocusListener, WindowListener{
	
   public static Logger account_log = Logger.getLogger(Frame_NewAccount.class);
	
   final int L_point = 30;
   Container CreateAccountFrame;
   Font font;
   JLabel label_main, label_id, label_pw, label_pwcf, label_name,label_phone,label_gender, label_birth, label_employ,label_create;
   JLabel imglabel_check_pw, imglabel_check_pwcf, label_separate1, label_separate2;
   JLabel label_year, label_month, label_date;
   static JTextField txt_id_input, txt_name_input, txt_firstphone_input, txt_secondphone_input, txt_thirdphone_input;
   JTextArea txt_infor;
   JPasswordField txt_pw_input, txt_pwcf_input;
   JButton btn_email_certify, btn_create, btn_cancel;
   ButtonGroup gr_gender, gr_employ, gr_agree;
   JRadioButton btn_man, btn_woman, btn_employer, btn_employee, btn_agree, btn_disagree;
   JComboBox<String> combo_year_input, combo_month_input, combo_date_input;
   String arrayYear[], arrayMonth[], arrayDate[];
   String data_id, data_pw, data_pwcf, data_name, data_phone ,data_gender, data_employ, data_birth_year,data_birth_month,data_birth_date, data_agree;
   public static int opencheck_newaccount = 0;
   
   Frame_CertifyEmail verify  =  new Frame_CertifyEmail();
   
   public Frame_NewAccount() {
	  account_log.info("Completed creation of member registration frame");
      setTitle("�����:ȸ������");
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setLocation(800,100);
      setLayout(null);
      setSize(400,860);
      setResizable(false);
      addWindowListener(this);
      opencheck_newaccount = 1;
      setCreateAccountFrame();
            
      setVisible(true);
   }

   private void setCreateAccountFrame() {
      CreateAccountFrame = getContentPane();
      CreateAccountFrame.setBackground(Color.WHITE);
      CreateAccountFrame.setLayout(null);
      
      setBirthArray();
      setFont();
      
      label_main = new JLabel("����� ȸ������", JLabel.CENTER);
      label_main.setSize(400, 40);
      label_main.setLocation(0, 0);
      label_main.setFont(new Font("���� ���", Font.BOLD, 18));
      label_main.setBackground(new Color(32,33,42));
      label_main.setForeground(Color.WHITE);
      label_main.setOpaque(true);
      
      label_id = new JLabel("���̵�(eMail)");
      label_id.setSize(100, 15);
      label_id.setLocation(L_point, 60);
      label_id.setFont(font);
      
      txt_id_input = new JTextField();
      txt_id_input.setBorder(new LineBorder(Color.BLACK));
      txt_id_input.setSize(200, 30);
      txt_id_input.setLocation(L_point,80);
      txt_id_input.setFont(font);
      
	  Image img_email_certify = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/�̸���������ư.png"));
      btn_email_certify = new JButton(new ImageIcon(img_email_certify));
      btn_email_certify.setSize(120, 36);
      btn_email_certify.setLocation(L_point+208,76);
      btn_email_certify.setBorderPainted(false);
      btn_email_certify.setContentAreaFilled(false);
      btn_email_certify.setFont(new Font("���� ���", Font.BOLD, 11));
      btn_email_certify.setCursor(new Cursor(Cursor.HAND_CURSOR));
      btn_email_certify.addActionListener(this);
      
      label_pw = new JLabel("��й�ȣ");
      label_pw.setSize(100, 15);
      label_pw.setLocation(L_point, 140);
      label_pw.setFont(font);
      
      txt_pw_input = new JPasswordField();
      txt_pw_input.setBorder(new LineBorder(Color.BLACK));
      txt_pw_input.setSize(280, 30);
      txt_pw_input.setLocation(L_point,160);
      txt_pw_input.setFont(font);
      txt_pw_input.addFocusListener(this);
      
      Image img_check_pw = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/üũ.png"));
      imglabel_check_pw = new JLabel(new ImageIcon(img_check_pw));
      imglabel_check_pw.setSize(30, 30);
      imglabel_check_pw.setLocation(L_point+290, 160);
      imglabel_check_pw.setEnabled(false);
      
      label_pwcf = new JLabel("��й�ȣ Ȯ��");
      label_pwcf.setSize(100, 15);
      label_pwcf.setLocation(L_point, 220);
      label_pwcf.setFont(font);
      
      txt_pwcf_input = new JPasswordField();
      txt_pwcf_input.setBorder(new LineBorder(Color.BLACK));
      txt_pwcf_input.setSize(280, 30);
      txt_pwcf_input.setLocation(L_point,240);
      txt_pwcf_input.setFont(font);
      txt_pwcf_input.addFocusListener(this);
      
      imglabel_check_pwcf = new JLabel(new ImageIcon(img_check_pw));
      imglabel_check_pwcf.setSize(30, 30);
      imglabel_check_pwcf.setLocation(L_point+290, 240);
      imglabel_check_pwcf.setEnabled(false);
      
      label_name = new JLabel("�̸�");
      label_name.setSize(100, 15);
      label_name.setLocation(L_point, 300);
      label_name.setFont(font);
         
      txt_name_input = new JTextField();
      txt_name_input.setBorder(new LineBorder(Color.BLACK));
      txt_name_input.setSize(320, 30);
      txt_name_input.setLocation(L_point,320);
      txt_name_input.setFont(font);
      
      label_phone = new JLabel("�޴���ȭ :");
      label_phone.setSize(100, 15);
      label_phone.setLocation(L_point, 380);
      label_phone.setFont(font);
      
      txt_firstphone_input = new JTextField("010", 1);
      txt_firstphone_input.setBorder(new LineBorder(Color.BLACK));
      txt_firstphone_input.setSize(60, 30);
      txt_firstphone_input.setLocation(L_point+80,374);
      txt_firstphone_input.setFont(font);
      
      label_separate1 = new JLabel("-");
      label_separate1.setSize(20, 15);
      label_separate1.setLocation(L_point+148, 380);
      label_separate1.setFont(font);
      
      txt_secondphone_input = new JTextField();
      txt_secondphone_input.setBorder(new LineBorder(Color.BLACK));
      txt_secondphone_input.setSize(60, 30);
      txt_secondphone_input.setLocation(L_point+160,374);
      txt_secondphone_input.setFont(font);
      
      label_separate2 = new JLabel("-");
      label_separate2.setSize(20, 15);
      label_separate2.setLocation(L_point+228, 380);
      label_separate2.setFont(font);
      
      txt_thirdphone_input = new JTextField();
      txt_thirdphone_input.setBorder(new LineBorder(Color.BLACK));
      txt_thirdphone_input.setSize(60, 30);
      txt_thirdphone_input.setLocation(L_point+240,374);
      txt_thirdphone_input.setFont(font);
      
      label_birth = new JLabel("������� :");
      label_birth.setSize(80, 15);
      label_birth.setLocation(L_point, 425);
      label_birth.setFont(font);
      
      combo_year_input = new JComboBox<String>(arrayYear);
      combo_year_input.setBackground(Color.WHITE);
      combo_year_input.setSize(60, 30);
      combo_year_input.setLocation(L_point+80, 419);
      
      label_year = new JLabel("��");
      label_year.setSize(30, 15);
      label_year.setLocation(L_point+145, 425);
      label_year.setFont(font);
      CreateAccountFrame.add(label_year);
      
      combo_month_input = new JComboBox<String>(arrayMonth);
      combo_month_input.setBackground(Color.WHITE);
      combo_month_input.setSize(40, 30);
      combo_month_input.setLocation(L_point+175, 419);
      
      label_month = new JLabel("��");
      label_month.setSize(30, 15);
      label_month.setLocation(L_point+220, 425);
      label_month.setFont(font);
      CreateAccountFrame.add(label_month);
      
      combo_date_input = new JComboBox<String>(arrayDate);
      combo_date_input.setBackground(Color.WHITE);
      combo_date_input.setSize(40, 30);
      combo_date_input.setLocation(L_point+245, 419);
      
      label_month = new JLabel("��");
      label_month.setSize(30, 15);
      label_month.setLocation(L_point+290, 425);
      label_month.setFont(font);
      CreateAccountFrame.add(label_month);
      
      label_gender = new JLabel("���� :");
      label_gender.setSize(50, 15);
      label_gender.setLocation(L_point, 470);
      label_gender.setFont(font);
      
      btn_man = new JRadioButton("����");
      btn_man.setSize(60, 30);
      btn_man.setLocation(L_point+60, 464);
      btn_man.setBackground(Color.WHITE);
      btn_man.setFont(font);
      btn_man.setCursor(new Cursor(Cursor.HAND_CURSOR));
      btn_man.addItemListener(this);
      
      btn_woman = new JRadioButton("����");
      btn_woman.setSize(60, 30);
      btn_woman.setLocation(L_point+130, 464);
      btn_woman.setBackground(Color.WHITE);
      btn_woman.setFont(font);
      btn_woman.setCursor(new Cursor(Cursor.HAND_CURSOR));
      btn_woman.addItemListener(this);
      
      gr_gender = new ButtonGroup();
      gr_gender.add(btn_man);
      gr_gender.add(btn_woman);
      
      label_employ = new JLabel("��å :");
      label_employ.setSize(50, 15);
      label_employ.setLocation(L_point, 510);
      label_employ.setFont(font);
      
      btn_employer = new JRadioButton("����");
      btn_employer.setSize(60, 30);
      btn_employer.setLocation(L_point+60, 504);
      btn_employer.setBackground(Color.WHITE);
      btn_employer.setFont(font);
      btn_employer.setCursor(new Cursor(Cursor.HAND_CURSOR));
      btn_employer.addItemListener(this);
      
      btn_employee = new JRadioButton("����");
      btn_employee.setSize(60, 30);
      btn_employee.setLocation(L_point+130, 504);
      btn_employee.setBackground(Color.WHITE);
      btn_employee.setFont(font);
      btn_employee.setCursor(new Cursor(Cursor.HAND_CURSOR));
      btn_employee.addItemListener(this);
      
      gr_employ = new ButtonGroup();
      gr_employ.add(btn_employer);
      gr_employ.add(btn_employee);
      
      txt_infor = new JTextArea("������� ���Ű� ȯ���մϴ�~\n����� ���� �� ��ǰ�� �̿��� �ּż� �����մϴ� �� ����� �پ��� ����� ������ �̿�� �����Ͽ� ����� ���񽺸� �����ϴ°���� �������θ�Ʈ�� �̸� �̿��ϴ� ����� ����ȸ�� �Ǵ� ��ȸ�������踦 �����ϸ�, �ƿ﷯ �������� ����� ���� �̿뿡 ������ �� �� �ִ������� ������ �����ϰ� �ֽ��ϴ�.\n" + 
            "1. �����ϴ� ��������\n" + "�̿��ڴ� ȸ�������� �ϼž� ����� ���񽺸� �̿��� �� �ֽ��ϴ�.������� ���� �̿��� ���� �ʿ��� �ּ����� ���������� �����մϴ�.ȸ������ �ÿ� '�̸���, ��й�ȣ, �̸�, �������, ����, ��ȭ��ȣ, �������� �̸���' �� �ʼ��׸����� �����մϴ�.");
      txt_infor.setSize(320, 120);
      txt_infor.setFont(new Font("���� ���", Font.BOLD, 12));
      txt_infor.setLineWrap(true);
      txt_infor.setEditable(false);
      txt_infor.setBorder(new LineBorder(Color.GRAY));
      
      JScrollPane sp = new JScrollPane(txt_infor);
      sp.setSize(320, 120);
      sp.setLocation(L_point, 550);
      
      btn_agree = new JRadioButton("�����մϴ�.");
      btn_agree.setCursor(new Cursor(Cursor.HAND_CURSOR));
      btn_agree.setSize(100, 30);
      btn_agree.setLocation(L_point, 680);
      btn_agree.setBackground(Color.WHITE);
      btn_agree.setFont(font);
      btn_agree.addItemListener(this);
      
      btn_disagree = new JRadioButton("�������� �ʽ��ϴ�.");
      btn_disagree.setCursor(new Cursor(Cursor.HAND_CURSOR));
      btn_disagree.setSize(150, 30);
      btn_disagree.setLocation(L_point+100, 680);
      btn_disagree.setBackground(Color.WHITE);
      btn_disagree.setFont(font);
      btn_disagree.addItemListener(this);
      
      gr_agree = new ButtonGroup();
      gr_agree.add(btn_agree);
      gr_agree.add(btn_disagree);
      
      Image img_create = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/�����ϱ�.png"));
      btn_create = new JButton(new ImageIcon(img_create));
      btn_create.setSize(100, 30);
      btn_create.setLocation(L_point+70,740);
      btn_create.setBorderPainted(false);
      btn_create.setContentAreaFilled(false);
      btn_create.setFont(font);
      btn_create.addActionListener(this);
      btn_create.setCursor(new Cursor(Cursor.HAND_CURSOR));
      
      Image img_cancel = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/���.png"));
      btn_cancel = new JButton(new ImageIcon(img_cancel));
      btn_cancel.setSize(80, 30);
      btn_cancel.setLocation(L_point+180,740);
      btn_cancel.setBorderPainted(false);
      btn_cancel.setContentAreaFilled(false);
      btn_cancel.setFont(font);
      btn_cancel.addActionListener(this);
      btn_cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
      
       label_create = new JLabel("(��)������������θ�Ʈ");
       label_create.setForeground(new Color(120, 120, 120));
       label_create.setFont(new Font("����", Font.BOLD, 12));
       label_create.setSize(150, 20);
       label_create.setLocation(5, 800);
       
      CreateAccountFrame.add(label_main);
      CreateAccountFrame.add(label_id);
      CreateAccountFrame.add(label_pw);
      CreateAccountFrame.add(imglabel_check_pw);
      CreateAccountFrame.add(label_pwcf);
      CreateAccountFrame.add(imglabel_check_pwcf);
      CreateAccountFrame.add(label_name);
      CreateAccountFrame.add(label_phone);
      CreateAccountFrame.add(label_gender);
      CreateAccountFrame.add(label_birth);
      CreateAccountFrame.add(label_employ);
      
      CreateAccountFrame.add(txt_id_input);
      CreateAccountFrame.add(txt_pw_input);
      CreateAccountFrame.add(txt_pwcf_input);
      CreateAccountFrame.add(txt_name_input);
      CreateAccountFrame.add(txt_firstphone_input);
      CreateAccountFrame.add(label_separate1);
      CreateAccountFrame.add(txt_secondphone_input);
      CreateAccountFrame.add(label_separate2);
      CreateAccountFrame.add(txt_thirdphone_input);
      
      CreateAccountFrame.add(combo_year_input);
      CreateAccountFrame.add(combo_month_input);
      CreateAccountFrame.add(combo_date_input);
      
      CreateAccountFrame.add(btn_email_certify);
      CreateAccountFrame.add(btn_man);
      CreateAccountFrame.add(btn_woman);
      
      CreateAccountFrame.add(btn_employer);
      CreateAccountFrame.add(btn_employee);
      CreateAccountFrame.add(btn_create);
      CreateAccountFrame.add(btn_cancel);
      
      CreateAccountFrame.add(btn_agree);
      CreateAccountFrame.add(btn_disagree);
      
      CreateAccountFrame.add(sp);
      
      CreateAccountFrame.add(label_create);
      
   }


    @SuppressWarnings("static-access")
	private void setFont() {
      font = new Font("���� ���", font.BOLD, 14);
   }
   
   private void setBirthArray() {
      arrayYear = new String[120];
      arrayMonth = new String[12];
      arrayDate = new String[31];
      for(int i = 0; i<120; i++) {
         arrayYear[i] = "" + (2019 - i);
      }
      for(int i = 0; i<12; i++) {
         arrayMonth[i] = "" + (1 + i);
      }
      for(int i = 0; i<31; i++) {
         arrayDate[i] = "" + (1 + i);
      }
      
   }

   @SuppressWarnings("deprecation")
   @Override
   public void actionPerformed(ActionEvent e) {
      Object act = e.getSource();
      if(act == btn_email_certify) {
    	  verify =  new Frame_CertifyEmail("�̸��� ����");
      }
      if(act == btn_create) {
         data_id = txt_id_input.getText();
         data_pw = txt_pw_input.getText();
         data_pwcf = txt_pwcf_input.getText();
         data_name = txt_name_input.getText();
         data_phone = txt_firstphone_input.getText() + txt_secondphone_input.getText() + txt_thirdphone_input.getText();
         data_birth_year = combo_year_input.getSelectedItem().toString();
         data_birth_month = combo_month_input.getSelectedItem().toString();
         data_birth_date = combo_date_input.getSelectedItem().toString();

         if(data_id.isEmpty() == true) {
        	new Frame_MessageBox("�����߻�", " ���̵� �Է����ּ���. ");
         }
         else if(data_pw.isEmpty() == true) {
        	new Frame_MessageBox("�����߻�", "��й�ȣ�� �Է����ּ���.");
         }
         else if(data_pwcf.isEmpty() == true) {
         	new Frame_MessageBox("�����߻�", "  ��й�ȣȮ���� �Է����ּ���.  ");
          }
         else if(data_name.isEmpty() == true) {
        	new Frame_MessageBox("�����߻�", "     �̸��� �Է����ּ���.     ");
         }
         else if(data_phone.isEmpty() == true) {
        	new Frame_MessageBox("�����߻�", "��ȭ��ȣ�� �Է����ּ���.");
         }
         else if(data_gender == null) {
        	new Frame_MessageBox("�����߻�", "  ������ �������ּ���.  ");
         }
         else if(data_employ == null) {
        	new Frame_MessageBox("�����߻�", "  ��å�� �������ּ���.  ");
         }
         else if(verify.getCheck() == false) {
        	new Frame_MessageBox("�����߻�", " �̸����� �������ּ���. ");
         }
         else if(data_agree == null || data_agree == "����") {
        	new Frame_MessageBox("�����߻�", "����� �������� �����̽��ϴ�.");
         }
         else {
        	Oracle_DAO Oracle = new Oracle_DAO();
        	if(data_employ.equals("����")) {
        		Oracle.OracleCreate(data_id, data_pw, data_name, data_phone, data_employ, data_gender, data_birth_year, data_birth_month, data_birth_date, -1, "N", "Empty" ,"0" ,"","");
        	}else {
        		Oracle.OracleCreate(data_id, data_pw, data_name, data_phone, data_employ, data_gender, data_birth_year, data_birth_month, data_birth_date, -1, "N", "Empty" , "", "","");
        	}
            dispose();
         }
      }
      
      if(act == btn_cancel) {
         dispose();
      }
      
   }

   @Override
   public void itemStateChanged(ItemEvent e) {
      Object select = e.getItem();
      if(select == btn_man) {
         data_gender = "����";
      }else if(select == btn_woman) {
         data_gender = "����";
      }
      
      if(select == btn_employer) {
         data_employ = "����";
      }else if(select == btn_employee) {
         data_employ = "����";
      }
      
      if(select == btn_agree) {
         data_agree = "����";
      }else if(select == btn_disagree) {
         data_agree = "����";
      }
   }
   
   @Override
   public void focusGained(FocusEvent e) {   
      //�̻��
   }
   
   @SuppressWarnings("deprecation")
   @Override
   public void focusLost(FocusEvent e) {
      if(txt_pw_input.getText().isEmpty() == false) {
         imglabel_check_pw.setEnabled(true);
      }else {
         imglabel_check_pw.setEnabled(false);
      }
      if(txt_pwcf_input.getText().isEmpty() == false && txt_pw_input.getText().equals(txt_pwcf_input.getText()) == true) {
         imglabel_check_pwcf.setEnabled(true);
      }else if(txt_pwcf_input.getText().isEmpty() == false && txt_pw_input.getText().equals(txt_pwcf_input.getText()) == false) {
         txt_pwcf_input.setText(null);
         imglabel_check_pwcf.setEnabled(false);
         new Frame_MessageBox("�����߻�", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
      }else if(txt_pwcf_input.getText().isEmpty() == true){
         imglabel_check_pwcf.setEnabled(false);
      }
      
   }

@Override
public void windowActivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosed(WindowEvent e) {
	opencheck_newaccount = 0;
	
}

@Override
public void windowClosing(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeactivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeiconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowIconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowOpened(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
}