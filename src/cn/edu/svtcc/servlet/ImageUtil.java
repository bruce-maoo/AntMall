package cn.edu.svtcc.servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpSession;
/**
 * ������֤��ͼƬ����
 * @author Shixuanming
 *	
 */
public class ImageUtil {
	//����һ�����������
	private static Random rand = new Random();
	//������֤��Ŀ��Ϊ120���߶�Ϊ50
	private static int width = 120;
	private static int height = 50;
	//��֤���е�4���ַ����Ǵ�����ַ��������
	private static char[] randChar = {'a','b','c','d','e','f','g',
			'h','i','j','k','l','m','n','o','p','q','r','s','t',
			'u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
	/**
	 * ������ά��ͼ��ķ���
	 * @param session servlet�е�session����
	 * @param length ��֤����ַ�����
	 * @return
	 */
	public static BufferedImage createImage(HttpSession session,int length) {
		//����һ��BufferedIamge�������ø�ͼƬ����Ŀ�ߣ���������������������ɫģ�飬��ȷ����������
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		//�������õ����ʶ���
		Graphics g = bi.getGraphics();
		//������֤�뱳��
		setBackground(g);
		//�������
		drawDot(g);
		//������֤���еĸ�����
		drawRandomLine((Graphics2D)g);
		//����һ��StringBuilder����
		StringBuilder sb = new StringBuilder();
		//forѭ��������Ϊ�ַ���
		for(int i=0;i<length;i++) {
			//���һ���������֣���ΧΪ0-����ַ�����ĳ���
			int index = rand.nextInt(randChar.length);
			//������ַ����������һ���ַ�������ӵ�StringBuilderĩβ
			sb.append(randChar[index]);
			//���û�����ɫ��ÿ���ַ�����һ���������ɫ
			g.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
			//����������ʽ����б�������С��40-50���
			g.setFont(new Font("΢���ź�",Font.ITALIC,rand.nextInt(10)+40));
			//��������ַ���ӵ�ͼƬ�У������������һ���ַ���drawStringд�����һ���ַ���������Ҫ���ַ�ǿתΪһ���ַ���
			//������Ϊi*25+10����������30-50���(���λ���Լ��Գ����ģ���Ҫ�Ǻÿ�)
			g.drawString(Character.toString(randChar[index]), i*25+10, rand.nextInt(20)+30);
		}
		//����ѭ���У�StringBuilder��Ӧ����4��������ַ�
		//���ö���ת��ΪString���ͣ����ŵ�session����
		/**
		 * ע������String��ֵ�ǲ��ɱ�ģ�����ÿ��ΪString��ֵ���ᴴ��һ���µ�String����
		 * StringBuilder�ǽ��ַ�������ĩβ��ӣ����ᴴ���µĶ��� 
		 * String��StringBuilder֮������໥ת��
		 * StringBuilder sb = new StringBuilder();
		 * ��Stringת��ΪStringBuilder  StringBuilder str = new StringBuilder(sb);
		 * ��StringBuilderתΪString  sb.toString();
		 */
		session.setAttribute("sb", sb.toString());
		//�رջ���
		g.dispose();
		//�������ɺõ���֤��ͼ��
		return bi;
	}
	/**
	 * ���ñ����ķ���
	 * @param g
	 */
	private static void setBackground(Graphics g) {
		//���û�����ɫ
		g.setColor(Color.white);
		//��һ�����Σ�����Ϊ0,0  ���ȺͿ��Ϊ��֤��ĳ��ȺͿ��
		//�þ��ξ��൱��һ������
		g.fillRect(0, 0, width, height);
	}
	/**
	 * ������ߵķ���
	 * @param g
	 */
	private static void drawRandomLine(Graphics2D g) {
		//���û��ʴ�ϸ
		g.setStroke(new BasicStroke(0.3F));
		//ѭ�����ξ��ǻ�������
		for(int i=0;i<5;i++) {
			//���û�����ɫ��ʵ�ʾ����ߵ���ɫ
			g.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
			//��ʼ��������
			int x1=rand.nextInt(width);
			int y1=rand.nextInt(height);
			//������������
			int x2=rand.nextInt(width);
			int y2=rand.nextInt(height);
			//����
			g.drawLine(x1, y1, x2, y2);
		}
	}
	/**
	 * �������ķ���
	 * @param g
	 */
	public static void drawDot(Graphics g) {
		//ͨ��һ��ѭ����Ƕ�ף�ʵ�ֺ���ÿ��5����һ������Ϊ1�ľ��Σ�����ÿ��5����һ������Ϊ1�ľ��Σ���Щ���ο���������һ����
		for(int i=1;i<height;i+=5) {
			//���������ɫ
			g.setColor(Color.BLACK);
			for(int j=1;j<width;j+=5) {
				g.fillRect(j, i, 1, 1);
			}
		}
	}
}
