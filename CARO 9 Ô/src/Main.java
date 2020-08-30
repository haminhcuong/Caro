import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static int sec = 0;
    private static Timer timer = new Timer();
    private static JLabel lblTime;
    private static Board board;
    private static JButton btnStart;

    public static void main(final String[] args) {
        final int width = 600;
        final int height = 600;
        // timer.cancel();

        board = new Board();
        board.setEndGameListener(new EndGameListener() {
            @Override
            public void end(String player, int st) {
                if (st == Board.ST_WIN) {
                    JOptionPane.showMessageDialog(null, "NGƯỜI CHƠI " + player + " THẮNG");
                    stopGame();
                } else if (st == Board.ST_DRAW) {
                    JOptionPane.showMessageDialog(null, "HOÀ");
                    stopGame();
                }
            }
        });

        JPanel jPanel = new JPanel(); // Tạo 1 cái JPanel là một cái khung lớn để chứa mấy đứa kia
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);// Các layout theo trục Y từ trên xuống
        jPanel.setLayout(boxLayout);

        board.setPreferredSize(new Dimension(width / 2, height / 2));// Set kích thước của Board

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);

        JPanel bottomPanel = new JPanel();// Tạo 1 cái Jpanel là một cái khung nhỏ ở dưới cùng
        bottomPanel.setLayout(flowLayout);
        bottomPanel.setBackground(Color.PINK);// Set màu hồng cho bottomPanel

        btnStart = new JButton("START");
        lblTime = new JLabel("0:0");
        bottomPanel.add(lblTime);
        bottomPanel.add(btnStart);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (btnStart.getText().equals("START")) {
                    startGame();
                } else
                    stopGame();
            }
        });

        jPanel.add(board);// add cái board vào khung lớn
        jPanel.add(bottomPanel);// add cái bottomPanel vào khung lớn

        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // Lấy kích thước của màn hình hiện tại
        final int x = (int) (dimension.getWidth() / 2) - (width / 2); // Lấy vị trí sao cho nằm giữa màn hình
        final int y = (int) (dimension.getHeight() / 2) - (height / 2); // Lấy vị trí sao cho nằm giữa màn hình
        final JFrame jFrame = new JFrame("CỜ CARO 9 Ô"); // Tạo tiêu đề
        jFrame.setSize(width, height); // Set kích thước của cửa sổ
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nhấn để thoát chương trình
        jFrame.setResizable(true); // Cho sửa kích thước của chương trình

        jFrame.add(jPanel);// Add khung lớn vào JFrame

        jFrame.setLocation(x, y);// Set vị trí của chương trình trong toạ độ là màn hình

        jFrame.pack();

        jFrame.setVisible(true); // Hiển thị chương trình
    }

    private static void startGame() {
        // Hỏi ai đi trước
        final int choice = JOptionPane.showConfirmDialog(null, "NGƯỜI CHƠI O ĐI TRƯỚC ĐÚNG KHÔNG ?",
                "CHỌN NGƯỜI CHƠI ĐI TRƯỚC", JOptionPane.YES_NO_OPTION);
        board.reset();
        String currentPlayer = (choice == 0) ? Cell.O_VALUE : Cell.X_VALUE;
        board.setCurrentPlayer(currentPlayer);

        // Đếm ngược
        sec = 0;
        lblTime.setText("0:0");
        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sec++;
                final String value = sec / 60 + ":" + sec % 60;
                lblTime.setText(value);
            }
        }, 1000, 1000);
        btnStart.setText("STOP");
    }

    private static void stopGame() {
        btnStart.setText("START");
        sec = 0;
        lblTime.setText("0:0");
        timer.cancel();
        timer = new Timer();
        board.reset();
    }
}
