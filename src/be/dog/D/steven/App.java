/*********************************************/
//            _               _____          //
//           | |             |  _  \         //
//         __| | ___   __ _  | | \  \        //
//        / _` |/ _ \ / _` | | |  \  \       //
//       | (_| | (_) | (_| | | |__/   |      //
//        \__,_|\___/ \__, | |______/        //
//                     __/ |                 //
//                    |___/                  //
/*********************************************/
package be.dog.D.steven;

import be.dog.D.steven.GUI.MainFrame;
import javax.swing.SwingUtilities;


class App {
    public static void main(String[] args) {

        // SINGLE THREAD
        //////////
        //      public void run() {
        //          new MainFrame();

        // More robust MULTITHREADING
        //////////
        // with 'Lambda':
                SwingUtilities.invokeLater(() -> new MainFrame());

        // OR with 'Method Reference':
        //      SwingUtilities.invokeLater(MainFrame::new);
    }
}
