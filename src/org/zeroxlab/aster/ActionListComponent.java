/*
 * Copyright (C) 2011 0xlab - http://0xlab.org/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authored by Kan-Ru Chen <kanru@0xlab.org>
 */

package org.zeroxlab.aster;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.Scrollable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import org.zeroxlab.aster.ui.ActionListUI;
import org.zeroxlab.aster.ui.BasicActionListUI;

/*
 * A Container like component for action button List
 */
public class ActionListComponent extends JComponent implements Scrollable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * The UI class ID string.
     */
    private static final String uiClassID = "ActionListUI";

    /**
     * Sets the new UI delegate.
     *
     * @param ui
     *            New UI delegate.
     */
    public void setUI(ActionListUI ui) {
        super.setUI(ui);
    }

    /**
     * Resets the UI property to a value from the current look and feel.
     *
     * @see JComponent#updateUI
     */
    public void updateUI() {
        if (UIManager.get(getUIClassID()) != null) {
            setUI((ActionListUI) UIManager.getUI(this));
        } else {
            setUI(new BasicActionListUI());
        }
    }

    /**
     * Returns the UI object which implements the L&F for this component.
     *
     * @return UI object which implements the L&F for this component.
     * @see #setUI
     */
    public ActionListUI getUI() {
        return (ActionListUI) ui;
    }

    /**
     * Returns the name of the UI class that implements the L&F for
     * this component.
     *
     * @return The name of the UI class that implements the L&F for this
     *         component.
     * @see JComponent#getUIClassID
     * @see UIDefaults#getUI
     */
    public String getUIClassID() {
        return uiClassID;
    }

    protected IActionListContoller actionListController;

    public ActionListComponent(IActionListContoller contoller) {
        this.actionListController = contoller;
        this.updateUI();
    }

    public IActionListContoller getActionListController() {
        return this.actionListController;
    }

    public void paintChildren(Graphics g) {
    }

    /**
     * @see Scrollable#getPreferredScrollableViewportSize()
     */
    public Dimension getPreferredScrollableViewportSize() {
        Dimension d = getPreferredSize();
        d.height = 100; // TODO: How to calculate the minimum size?
        return d;
    }

    /**
     * @see Scrollable#getScrollableBlockIncrement(java.awt.Rectangle, int, int)
     */
    public int getScrollableBlockIncrement(Rectangle visibleRect,
                                           int orientation, int direction) {
        return 20;
    }

    /**
     * @see Scrollable#getScrollableTracksViewportHeight()
     */
    public boolean getScrollableTracksViewportHeight() {
        if (getParent() instanceof  JViewport) {
            return (((JViewport) getParent()).getHeight() > getPreferredSize().height);
        } else {
            return false;
        }
    }

    /**
     * @see Scrollable#getScrollableTracksViewportWidth()
     */
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    /**
     * @see Scrollable#getScrollableUnitIncrement(java.awt.Rectangle, int, int)
     */
    public int getScrollableUnitIncrement(Rectangle visibleRect,
                                          int orientation, int direction) {
        return 20;
    }

    public void addNewActionListener(MouseListener l) {
        ((ActionListUI)ui).addNewActionListener(l);
    }

    public void removeNewActionListener(MouseListener l) {
        ((ActionListUI)ui).removeNewActionListener(l);
    }
}
