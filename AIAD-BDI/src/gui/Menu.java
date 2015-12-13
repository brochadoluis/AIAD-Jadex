package gui;

import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.types.cms.CreationInfo;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.future.IFuture;
import jadex.commons.future.ThreadSuspendable;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class Menu {
	
	IComponentManagementService cms;
	ThreadSuspendable sus;

	public Menu(IComponentManagementService cms, ThreadSuspendable sus) {
		this.cms=cms;
		this.sus=sus;

		initialize();
	}

	private void initialize() {
		
		IComponentIdentifier wid = cms.createComponent("Fighter", "agents/FighterBDI.class", null).getFirstResult(sus);
		System.out.println("Started simulation component: fighter");
		
		IComponentIdentifier cID = cms.createComponent("Command", "agents/CommandBDI.class", null).getFirstResult(sus);
		System.out.println("Started simulation component: Command");
		
		IComponentIdentifier wID = cms.createComponent("Watcher", "agents/WatcherBDI.class", null).getFirstResult(sus);
		System.out.println("Started simulation component: watcher");		
	}


}
