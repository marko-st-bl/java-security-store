package org.unibl.etf.bp.store.app;

import java.awt.EventQueue;

import javax.swing.UIManager;

import org.unibl.etf.bp.store.gui.ZaposleniMain;


public class Application {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZaposleniMain frame = new ZaposleniMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
