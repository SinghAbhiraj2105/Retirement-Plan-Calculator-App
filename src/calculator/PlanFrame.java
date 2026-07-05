package calculator;

/*
Retirement Account Calculator
CSCI 185
Contributors: Franko Nava-Islas
Last Edited: 5/11/2026
 */

import javax.swing.*;
import java.awt.*;

public class PlanFrame extends JFrame {
    private final JComboBox<String> plans = new JComboBox<>(new String[]{
            "401k Traditional", "401k Roth", "IRA Traditional", "IRA Roth"});
    private JButton submit;
    private JLabel kDesc;
    private JLabel iraDesc;

    public PlanFrame(){
        setTitle("Retirement Account Calculator");
        setSize(1050, 550);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // start at center

        initItems();
        layoutItems();

        submit.addActionListener(p -> nextPage());
    }

    private void initItems(){
        submit = new JButton("Choose Plan");
        kDesc = new JLabel("<html>A 401k retirement plan allows you to contribute part of your salary towards a retirement account. An employer matches a specific percentage of your salary and contributes it to the account.This calculator can calculate two different plan types of 401k: Traditional 401k and Roth 401k. A Traditional 401k plan has non-taxed contributions which allows for tax-deferred growth but the money withdrawn at retirement is taxed. A Roth 401k plan has taxed contributions but the money withdrawn at retirement is not taxed.<br><br>Employer Matches: Employers' matches can range from 25% to 100% of what you've contributed to the account.<br>Contribution Limits: If you are younger than 50, the maximum you can contribute is $24,500 yearly. People older than 50 can contribute $32,500 yearly.<br>Withdrawal Rules: Early withdrawals (withdraw before age 55) will incur a 10% penalty fee. Penalty-free withdrawals start at age 59.</html>");
        iraDesc = new JLabel("<html>An IRA account is a retirement account that allows you to save for retirement independently of an employer. This calculator can calculate two different plan types of IRA: Traditional IRA and Roth IRA. A Traditional IRA plan has non-taxed contributions which allows for tax-deferred growth but the money withdrawn at retirement is taxed. A Roth IRA plan has taxed contributions but the money withdrawn at retirement is not taxed.<br><br>Contribution Limits: You can contribute $7,500 yearly if you are below the age of 50, and it raises to $8,600 if you above 50.<br>Withdrawal Rules: Early withdrawals (withdraw before age 55) will incur a 10% penalty fee. Penalty-free withdrawals start at age 59.</html>");
    }

    private void layoutItems(){
        LayoutManager mgr = new GridLayout(2, 2, 20, 20);
        setLayout(mgr);

        add(kDesc);
        add(iraDesc);
        add(plans);
        add(submit);
    }

    private void nextPage(){
        if (plans.getSelectedIndex() == 0){
            // 401k Traditional Calc Frame.setVisible(true)
            SwingUtilities.invokeLater(() -> new Traditional401kGUI().setVisible(true));
        }
        else if (plans.getSelectedIndex() == 1){
            // 401k Roth Calc Frame.setVisible(true)
            SwingUtilities.invokeLater(() -> new Roth401kGUI().setVisible(true));
        }
        else if (plans.getSelectedIndex() == 2){
            // IRA Traditional
            SwingUtilities.invokeLater(() -> new TraditionalIRAGUI().setVisible(true));
        }
        else if (plans.getSelectedIndex() == 3){
            // IRA Roth
            SwingUtilities.invokeLater(() -> new RothIRAGUI().setVisible(true));

        }
        dispose();
    }
}