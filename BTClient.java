import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.text.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.swing.border.*;
import javax.sound.sampled.*;
import java.net.*;

public class BTClient extends JFrame implements ActionListener {
   Container cp;   
   JLabel label_id,chat,myscore,charbar,answer,JLclient1,JLclient2,JLclient3,JLclient4;
   Label Lclient1, Lclient2, Lclient3, Lclient4;
   JButton B_ready,B_exit,quiz,send;
   JTextArea T_chatPanel, T_mainPanel, T_myscore;
   JTextField T_chatInput;
   JScrollPane scrP_chat;
   JPanel pn;
   int port = 2222;
   Socket soc;
   String name, score, idx,charac,sendmsg,line,genre,songNumber,myName, authorName;
   boolean gamestart, author;
   int gameNumber;
   int i = 0;
   Clip clip;
   ImageIcon[] imgs = new ImageIcon[3];
   ImageIcon[] imgs2 = new ImageIcon[3];
   Vector<String> songtxt = new Vector<String>();
   String[] files = new String[14];

   public BTClient(){
	   init();
	   enter();
   }
    void init(){
	   cp = getContentPane();
       cp.setLayout(null);
       pn = new JPanel();
       pn.setLayout(null);
       pn.setBounds(0,0,1200,700);
       label_id = new JLabel();
       label_id.setIcon(new ImageIcon("imgs//bg.png"));//���
      
       B_ready = new JButton(new ImageIcon("imgs//ready-2.png"));//��ư
       B_ready.setBorderPainted(false);
       B_ready.setOpaque(false);
       B_ready.setFocusPainted(false);
	   B_ready.setContentAreaFilled(false);
      
       B_exit = new JButton(new ImageIcon("imgs//exit-2.png"));//��ư
       B_exit.setBorderPainted(false);
       B_exit.setOpaque(false);
       B_exit.setFocusPainted(false);
	   B_exit.setContentAreaFilled(false);
       B_exit.addActionListener(this);

	   send = new JButton(new ImageIcon("imgs//addmusic.png"));//��ư
       send.setOpaque(false);
	   send.setBorderPainted(false);
	   send.setFocusPainted(false);
       send.setContentAreaFilled(false);
	   send.addActionListener(this);
      
       charbar = new JLabel(new ImageIcon("imgs//charBar.png"));//��ư
       charbar.setOpaque(false);
     
       myscore = new JLabel(new ImageIcon("imgs//score.png"));//������ �̹���
       myscore.setOpaque(false);
      
       quiz = new JButton(new ImageIcon("imgs//quiz.png"));//��ư
       quiz.setBorderPainted(false);
       quiz.setOpaque(false);
	   quiz.setContentAreaFilled(false);
       quiz.setFocusPainted(false);
       quiz.addActionListener(this);
      
       chat = new JLabel(new ImageIcon("imgs//chat.png"));//ä�� ��.
       chat.setOpaque(false);
      
       answer = new JLabel(new ImageIcon("imgs//answer.png"));//��ư
       answer.setOpaque(false);

	   T_mainPanel = new JTextArea();
	   T_mainPanel.setOpaque(false);
	   T_mainPanel.setFont(new Font("������� ExtraBold", Font.PLAIN, 25));
	   T_mainPanel.setEditable(false);
	   T_mainPanel.setForeground(Color.WHITE);

	   T_myscore = new JTextArea();
	   T_myscore.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
	   T_myscore.setEditable(false);

	   T_chatInput = new JTextField();
	   T_chatInput.setOpaque(false);
	   T_chatInput.setFont(new Font("�����ٸ����", Font.BOLD, 25));
	   T_chatInput.setText("");
	   T_chatInput.setBorder(null);
	   T_chatInput.setForeground(Color.WHITE);

	   T_chatPanel = new JTextArea();
	   T_chatPanel.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
	   T_chatPanel.setBorder(null);
	   T_chatPanel.setEditable(false);
	   T_chatPanel.setForeground(Color.WHITE);

	   scrP_chat = new JScrollPane(T_chatPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	   scrP_chat.setBounds(70,400,155,130);

       scrP_chat.getViewport().setOpaque(false);
       scrP_chat.setOpaque(false);
       T_chatPanel.setOpaque(false);


	   JPanel Pclientlist = new JPanel();
	   JLabel Lclientlist = new JLabel(new ImageIcon("imags//imgs.png"));
	   Pclientlist.setOpaque(false);
	   Lclientlist.setOpaque(false);
	   Pclientlist.setBorder(new LineBorder(new Color(127, 219, 254), 4, true));
       Pclientlist.setBounds(50, 20, 220, 260);
       Lclientlist.setBounds(50, 20, 220, 260);
	   
	   JLclient1 = new JLabel(new ImageIcon("imgs//cc4-mini.png"));
       JLclient1.setBounds(15, 20, 90, 70); 
	   Pclientlist.add(JLclient1);
	   
	   Lclient1 = new Label("[ �г��� & ���� ]");
	   Lclient1.setFont(new Font("�����ٸ����", Font.BOLD, 11));
	   Lclient1.setAlignment(Label.CENTER);
       Lclient1.setBounds(10, 90, 100, 30);
	   Pclientlist.add(Lclient1);

       JLclient2 = new JLabel(new ImageIcon("imgs//cc5-mini.png"));
       JLclient2.setBounds(115, 20, 90, 70);
       Pclientlist.add(JLclient2);
	   
	   Lclient2 = new Label("[ �г��� & ���� ]");
	   Lclient2.setFont(new Font("�����ٸ����", Font.BOLD, 11));
	   Lclient2.setAlignment(Label.CENTER);
       Lclient2.setBounds(115, 90, 100, 30);
	   Pclientlist.add(Lclient2);
	   
	   JLclient3 = new JLabel(new ImageIcon("imgs//cc6-mini.png"));
       JLclient3.setBounds(15, 140, 90, 70);
	   Pclientlist.add(JLclient3);
	   
	   Lclient3 = new Label("[ �г��� & ���� ]");
	   Lclient3.setFont(new Font("�����ٸ����", Font.BOLD, 11));
	   Lclient3.setAlignment(Label.CENTER);
       Lclient3.setBounds(10, 210, 100, 30);
	   Pclientlist.add(Lclient3);
	   
	   JLclient4 = new JLabel(new ImageIcon("imgs//cc7-mini.png"));
       JLclient4.setBounds(115, 140, 90, 70); 
	   Pclientlist.add(JLclient4);
	   
	   Lclient4 = new Label("[ �г��� & ���� ]");
	   Lclient4.setFont(new Font("�����ٸ����", Font.BOLD, 11));
	   Lclient4.setAlignment(Label.CENTER);
       Lclient4.setBounds(115, 210, 100, 30);
	   Pclientlist.add(Lclient4);
	   Pclientlist.setLayout(null);

       label_id.setBounds(0, 0, 1200, 700);
       B_ready.setBounds(760, 0, 200, 71);
       B_exit.setBounds(960, 0, 200, 71);
       charbar.setBounds(40, 20, 4, 260);
       myscore.setBounds(920, 385, 230, 245);
       quiz.setBounds(295, 100, 600, 400);
       chat.setBounds(40, 295, 230, 330);
       answer.setBounds(335, 500, 520, 130);
	   send.setBounds(935,290,200,71);
	   T_mainPanel.setBounds(330,200,520,270);
	   T_chatPanel.setBounds(50,315,200,300);
	   T_chatInput.setBounds(370,535,450,80);
	   scrP_chat.setBounds(50,315,200,300);

	   pn.add(scrP_chat);
	   pn.add(T_chatInput);
	   pn.add(T_mainPanel);
	   pn.add(answer);
	   pn.add(Pclientlist);
	   pn.add(Lclientlist);
	   pn.add(B_ready);
       pn.add(B_exit);
	   pn.add(send);
       pn.add(charbar);
       pn.add(myscore);
       pn.add(quiz);
       pn.add(chat);
	   pn.add(T_myscore);

	   pn.add(label_id);
       cp.add(pn);

	   setUI();
    }

    void setUI(){
       setTitle("Drop the Beat");
       setSize(1200,700);
       setVisible(true);
       setResizable(false);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setLocationRelativeTo(null);
	   imgs[0] = new ImageIcon("imgs//cc4-mini.png");
       imgs[1] = new ImageIcon("imgs//cc5-mini.png");
       imgs[2] = new ImageIcon("imgs//cc6-mini.png");
	   imgs2[0] = new ImageIcon("imgs//cc4.png");
	   imgs2[1] = new ImageIcon("imgs//cc5.png");
	   imgs2[2] = new ImageIcon("imgs//cc6.png");
    }

    void enter(){
	    String nick = Start.Nickname; //���⼭ �α��� Ŭ������ ������ �޾ƿ�. 
		String ip = Start.ip;
		myName = Start.Nickname;
		int charac = Start.charac;

	    try{
		    Socket soc = new Socket(ip,port);
		    Speaking speak = new Speaking(soc,nick,charac);
		    Listening listen = new Listening(soc); // ���� Ʋ �غ�
			speak.start();
			listen.start();
			B_ready.addActionListener(new Speaking(soc,nick,charac));
			send.addActionListener(new Speaking(soc,nick,charac));
			T_chatInput.addKeyListener(new Speaking(soc,nick,charac));
			//System.out.println("�������Ἲ��!!!!!!");
 	    }catch(IOException io){
  			JOptionPane.showMessageDialog(null, "���� ���� ����!\n������ ���� �ִ� �� �����ϴ�.", "ERROR", JOptionPane.WARNING_MESSAGE);
 			System.exit(0);
 	   }
   }

   public void actionPerformed(ActionEvent e){
       Object obj = e.getSource();
       if(obj == B_exit){
           int answer= JOptionPane.showConfirmDialog(null, "���� ���� �Ͻðڽ��ϱ�?", "����â",
                       JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
           if(answer == JOptionPane.YES_OPTION) System.exit(0);
	   }
   }

   class Speaking extends Thread implements KeyListener, ActionListener {
	   DataOutputStream dos;
	   String nick;
	   Socket soc;
	   int charac;

	   Speaking(Socket soc, String nick, int charac){
		   this.soc = soc;
		   try{
			   dos = new DataOutputStream(this.soc.getOutputStream());
			   this.nick = nick;
			   this.charac = charac;
		   }catch(IOException ie){ }
	   }
	  
	   public void run(){
		   try{
			   dos.writeUTF(nick+"/"+charac);
		   }catch(IOException ii){ }
	   }
	   public void keyReleased(KeyEvent e){
		   //P.pln("����");
		   if(e.getKeyCode() == KeyEvent.VK_ENTER){
			   String msg = T_chatInput.getText();
			   T_chatInput.setText("");
			   try{
					dos.writeUTF("@" + Setting.CHAT + nick + " : " + msg);
					dos.flush();
				}catch(IOException io){ }
		   }
	   }
	   public void actionPerformed(ActionEvent e){
		   Object obj = e.getSource();
		   if(e.getSource() == B_ready){
			   try{
					dos.writeUTF("@" + Setting.CHAT + " * "+ nick + " �� �غ� �Ϸ� !!");
					dos.flush();
					dos.writeUTF("@"+Setting.READY);
					dos.flush();
					B_ready.setEnabled(false);
				}catch(IOException io){ }
		   }else if(obj == send){
			   try{
					sendmsg= JOptionPane.showInputDialog(null, "� ��û���� ���Ͻó���?", " ");
					JOptionPane.showMessageDialog(null, "<"+sendmsg+"> ��û���� ������ �����߽��ϴ�!\n���� ���ϳ��� ���� �ϰڽ��ϴ�.");
					dos.writeUTF("@" + Setting.SEND + sendmsg);
					dos.flush();
				}catch(IOException ie){ }
			}
	   }
	   public void keyTyped(KeyEvent e){}
	   public void keyPressed(KeyEvent e){}
   }

   class Listening extends Thread {
	   DataInputStream dis;
	   DataOutputStream dos;
	   Socket soc; 
	   Listening(Socket soc){
		   this.soc = soc;
		   try{
				dis = new DataInputStream(this.soc.getInputStream());
				dos = new DataOutputStream(this.soc.getOutputStream());
			}catch(IOException io){ }
	   }
	   void txtSet(){
		   String song0 ="_ ___ __ _____\n_ __ ___ ��� YES or YES?";
		   String song1 = "Shoes on, get up in the morn' Cup of milk, \nlet's rock and roll King Kong, kick the drum,\nrolling on like a Rolling Stone\nSing song when I'm walking home \nJump up to the top, LeBron \nDing dong, ____ __ __ __ _____ \nIce tea and a game of ping pong";
		   String song2 ="���������δ� �� �˰� ���ݾ�\n____ __ __ _____ .";
		   String song3 ="���ݺ��� �����غ� ���� �޷�\n_______ _______ __ ____\n���� �� �� �־�\n������ �޷��� ��ģ ���� ��";
		   String song4 ="___ ___ ___ _____\n���� ���� ���� �� ����";
		   String song5 ="���� ������� ������ motown\n������� ���� �ӿ� ����\n�� �游ŭ �װ�\n_____ ____ __\n������ �Ǿ���\n�߰����� �쿬�� 5�� ���� ��Ʈ";
		   String song6 ="������ �� �ʹ� �η���\n���� �� ���� ����\nWhen open my eyes, � �͵� �� ���̴� ��\n__ ____ __ _ ___ __ .\n����, �� �Ͼ ��\n������ �ƹ��� ����";
		   String song7 ="Baby, �� �ʸ� ���\n___ ___ __ ___ _ .";
		   String song8 ="__ __ ____\n_ __ __ ____\nTell me what I got to do\n���� ��� ������� ��\n�ƹ� �뷡�� �ϴ� Ʋ��";
		   String song9 ="���� ������� ȥ�� �Դ�\n__ __ __ __ __ __\n���� ���� ���� ������\n������ �湮�� ������� ����";
		   String song10 ="�׷� �Ѷ� � ���� �ߵ��� ����\n__ __ __ __ ___ ������";
		   String song11 ="___ ___ ___ ___ __";
		   String song12 ="__ __ ____ __ ___\n���� �ߴ� �츮 �ƹ���";
		   String song13 ="____ ____\n__ _ __ ____ .";
		   String song14 ="____ ___ __ ___\n___ __ ___";
		   songtxt.add(song0);
		   songtxt.add(song1);
		   songtxt.add(song2);
		   songtxt.add(song3);
		   songtxt.add(song4);
		   songtxt.add(song5);
		   songtxt.add(song6);
		   songtxt.add(song7);
		   songtxt.add(song8);
		   songtxt.add(song9);
		   songtxt.add(song10);
		   songtxt.add(song11);
		   songtxt.add(song12);
		   songtxt.add(song13);
		   songtxt.add(song14);
	   }
	   public void run(){
		   while(dis != null){
			   try{
				   String msg = dis.readUTF();
				   String code = msg.substring(0,3);
				   if(code.equals("#"+Setting.RESET)){
					   name = msg.substring(3,msg.indexOf("/"));
					   score = msg.substring(msg.indexOf("/")+1, msg.indexOf(","));
					   charac = msg.substring(msg.indexOf(",")+1,msg.indexOf(" "));
					   idx = msg.substring(msg.indexOf(" ")+1);
					   renewalList(); // Ŭ���̾�Ʈ ����Ʈ ����
				   }else if(code.equals("#"+Setting.GAME_START)){
					   gamestart=true;
				   }else if(code.equals("#"+Setting.GAME_OVER)){
					   gamestart=false;
					   author = false;
					   B_ready.setEnabled(true);
				   }else if(code.equals("#"+Setting.AUTHOR)){
					   authorName = msg.substring(3);
					   P.pln("AUTHOR:"+msg.substring(3));
					   if(Start.Nickname.equals(msg.substring(3))){
						   author = true;
						   String []genres = {"����", "��", "Ʈ��Ʈ", "����"};
						   int selected = JOptionPane.showOptionDialog(null, "� �帣�� �����Ͻðڽ��ϱ�?", "�帣 ����", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, genres, genres[0]);
						   if(selected == JOptionPane.CLOSED_OPTION) JOptionPane.showMessageDialog(null, "�ٽ� �������ּ���!");
						   else JOptionPane.showMessageDialog(null, genres[selected]+" �帣�� �����ϼ̽��ϴ�!");
						   String select = genres[selected];
						   try{ // ���õ� �帣�� �������� ������.
							   dos.writeUTF("@"+Setting.CHOOSE+select);
							   dos.flush();
						   }catch(IOException ii){ }
					   }else author = false;
				   }else if (code.equals("#"+Setting.CHAT)){
					   //P.pln("ä�� :"+msg);
					   T_chatPanel.append(msg.substring(3)+"\n");
					   scrP_chat.getVerticalScrollBar().setValue(scrP_chat.getVerticalScrollBar().getMaximum());
				   }else if (code.equals("#"+Setting.COUNT)){
					   T_mainPanel.setText(msg.substring(3)+"\n");
					   scrP_chat.getVerticalScrollBar().setValue(scrP_chat.getVerticalScrollBar().getMaximum());
				   }else if (code.equals("#"+Setting.MUSIC)){ //������ȣ ���� > ���
					   File folder10 = new File("sound/10");
					   File SongList[] = folder10.listFiles();
					   txtSet(); //��0-4, ����5-9, Ʈ��Ʈ10-14
					   songNumber = msg.substring(msg.indexOf("/")+1);
					   int songNum = Integer.parseInt(songNumber);
					   T_mainPanel.setText(songtxt.get(songNum)); // ���� ȭ�鿡�� �� �߳�?
					   if(msg.substring(3).equals("0")){
						   try{ // �ش� ��ȣ�� �뷡�� Ʈ�� ��
							   clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
							   clip.open(AudioSystem.getAudioInputStream(SongList[songNum]));
							   clip.addLineListener(event -> { 
								   if (event.getType() == LineEvent.Type.STOP) clip.close();
								   });
							   clip.start();
							   while(!clip.isRunning()) Thread.sleep(10);
							   while (clip.isRunning()) Thread.sleep(10);
							   clip.close();
							   if(myName.equals(authorName)){
								   try{ // �뷡 1ȸ�� ��� �� �ƴٰ� �˷��ֱ�.
									   dos.writeUTF("@"+Setting.NEXT+"1");
									   dos.flush();
								   }catch(IOException ii){ }
							   }
						   }catch(LineUnavailableException le){
						   }catch(InterruptedException ie){
						   }catch(UnsupportedAudioFileException ue){ }
					   }else if(msg.substring(3).equals("1")){
						   try{ // �ش� ��ȣ�� �뷡�� Ʈ�� ��
							   clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
							   clip.open(AudioSystem.getAudioInputStream(SongList[songNum]));
							   clip.addLineListener(event -> { 
								   if (event.getType() == LineEvent.Type.STOP) clip.close();
								   });
							   clip.start();
							   while(!clip.isRunning()) Thread.sleep(10);
							   while (clip.isRunning()) Thread.sleep(10);
							   clip.close();
							   if(myName.equals(authorName)){
								   try{ // �뷡 1ȸ�� ��� �� �ƴٰ� �˷��ֱ�.
									   dos.writeUTF("@"+Setting.NEXT+"1");
									   dos.flush();
								   }catch(IOException ii){ }
							   }
						   }catch(LineUnavailableException le){
						   }catch(InterruptedException ie){
						   }catch(UnsupportedAudioFileException ue){ }

					   }else if(msg.substring(3).equals("2")){
						   try{ // �ش� ��ȣ�� �뷡�� Ʈ�� ��
							   clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
							   clip.open(AudioSystem.getAudioInputStream(SongList[songNum]));
							   clip.addLineListener(event -> { 
								   if (event.getType() == LineEvent.Type.STOP) clip.close();
								   });
							   clip.start();
							   while(!clip.isRunning()) Thread.sleep(10);
							   while (clip.isRunning()) Thread.sleep(10);
							   clip.close();
							   if(myName.equals(authorName)){
								   try{ // �뷡 1ȸ�� ��� �� �ƴٰ� �˷��ֱ�.
									   dos.writeUTF("@"+Setting.NEXT+"1");
									   dos.flush();
								   }catch(IOException ii){ }
							   }
						   }catch(LineUnavailableException le){
						   }catch(InterruptedException ie){
						   }catch(UnsupportedAudioFileException ue){ }
					   }
				   }else if (code.equals("#"+Setting.QUIZ)) T_mainPanel.setText(msg.substring(3)+"\n");
			   }catch(IOException ii){
				  T_chatPanel.append("[ �������� ������ ���������ϴ�. �г��� �ߺ�, ���� ���� �ʰ�, ���� �������� ��� ������ �źε˴ϴ�. ]\n[ 3�� �� ���α׷��� �����մϴ� .. ]");
				   try{
					   Thread.sleep(3000);
					   System.exit(0);
				   }catch(InterruptedException it){ }
			   }
		   }
	   }
	   public void renewalList(){
         int indexN = Integer.parseInt(idx);
         int charaN = Integer.parseInt(charac);
         if(indexN == 0){
            JLclient1.setIcon(imgs[charaN]);
            Lclient1.setText("[" + name + " / " + "����: " + score + "]");
            if(name.equals(Start.Nickname)){
				myscore.setIcon(imgs2[charaN]);
				myscore.setText("[" + name + " / " + "����: " + score + "]");
				myscore.setFont(new Font("�����ٸ����", Font.BOLD, 16));
				myscore.setForeground(Color.WHITE);
				myscore.setHorizontalAlignment(JLabel.CENTER);
            }
			deleteList();
		 }else if(indexN == 1){
            JLclient2.setIcon(imgs[charaN]);
			Lclient2.setText("[ " + name + " / " + "����: " + score + " ]");
            if(name.equals(Start.Nickname)){
				myscore.setIcon(imgs2[charaN]);
				myscore.setText("[" + name + " / " + "����: " + score + "]");
				myscore.setFont(new Font("�����ٸ����", Font.BOLD, 16));
				myscore.setForeground(Color.WHITE);
				myscore.setHorizontalAlignment(JLabel.CENTER);
            }         
            deleteList();
		 }else if(indexN == 2){
            JLclient3.setIcon(imgs[charaN]);
			Lclient3.setText("[ " + name + " / " + "����: " + score + " ]");
            if(name.equals(Start.Nickname)){
				myscore.setIcon(imgs2[charaN]);
				myscore.setText("[" + name + " / " + "����: " + score + "]");
				myscore.setFont(new Font("�����ٸ����", Font.BOLD, 16));
				myscore.setForeground(Color.WHITE);
				myscore.setHorizontalAlignment(JLabel.CENTER);
            }         
            deleteList();
		 }else if(indexN == 3){
			JLclient4.setIcon(imgs[charaN]);
			Lclient4.setText("[ " + name + " / " + "����: " + score + " ]");
            if(name.equals(Start.Nickname)){
				myscore.setIcon(imgs2[charaN]);
				myscore.setText("[" + name + " / " + "����: " + score + "]");
				myscore.setFont(new Font("�����ٸ����", Font.BOLD, 16));
				myscore.setForeground(Color.WHITE);
				myscore.setHorizontalAlignment(JLabel.CENTER);
            }
            deleteList();
		 }
	   }
	   /* ĳ���� �ݺ��� �̰ɷ� ��ġ��.
	   void listset(){
	   }*/
	   public void deleteList(){
		   int indexN = Integer.parseInt(idx);
		   int charaN = Integer.parseInt(charac);
		   switch(indexN){ 
			   case 0: Lclient2.setText("[ ���Ӵ�� / ���� ]"); Lclient3.setText("[ ���Ӵ�� / ���� ]"); Lclient4.setText("[ ���Ӵ�� / ���� ]");
			   case 1: Lclient3.setText("[ ���Ӵ�� / ���� ]"); Lclient4.setText("[ ���Ӵ�� / ���� ]");
			   case 2: Lclient4.setText("[ ���Ӵ�� / ���� ]");
		   }
	   }
    }
	public static void main(String[] args){
		BTClient btc = new BTClient();
	}
}