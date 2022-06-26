package MUSIC;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.swing.JRadioButton;

import javazoom.jl.decoder.JavaLayerException;

public class PlayList {

    Thread t;

    Node head, tail;

    Node current;

    FileInputStream file;

    javazoom.jl.player.Player player;

    boolean loop = true;

    JRadioButton loopbtn;

    public PlayList(JRadioButton loopbtn) {

        this.head = this.tail = null;

        loopbtn.addActionListener((e) -> {

            loop = loopbtn.isSelected();

            System.out.println(loop);

        });

    }

    public void next() throws FileNotFoundException, JavaLayerException {

        t.stop();

        player.close();

        if (current.next != null) {

            current = current.next;

            topEl(current);

        } else {

            current = head;

            topEl(current);

        }

    }

    public void prev() throws FileNotFoundException, JavaLayerException {

        t.stop();

        player.close();

        if (current.prev != null) {

            current = current.prev;

            topEl(current);

        } else {

            current = tail;

            topEl(current);

        }

    }

    public void topEl(Node node) throws FileNotFoundException, JavaLayerException {

       

        t = new Thread(() -> {

            current = node == null ? this.head : node;

            while (current != null) {

                FileInputStream track;

                try {

                    track = new FileInputStream(current.FilePath);

                    try {

                        player = new javazoom.jl.player.Player(track);

                    } catch (JavaLayerException ex) {

                        Logger.getLogger(PlayList.class.getName()).log(Level.SEVERE, null, ex);

                    }

                } catch (FileNotFoundException ex) {

                    Logger.getLogger(PlayList.class.getName()).log(Level.SEVERE, null, ex);

                }

                try {

                    player.play();

                } catch (JavaLayerException ex) {

                    Logger.getLogger(PlayList.class.getName()).log(Level.SEVERE, null, ex);

                }

                if (current.next == null && loop) {

                    current = head;

                } else {

                    current = current.next;

                }

            }

        });

        t.start();

    }

    public void stopp() {

        player.close();

    }

    public boolean isEmpty() {

        return this.head == null;

    }

    public void addToEnd(String FilePath) {

        Node node = new Node(FilePath);

        if (!isEmpty()) {

            this.tail.next = node;

            node.prev = this.tail;

            this.tail = node;

        } else {

            this.head = this.tail = node;

        }

    }

    public void printList() {

        for (Node temp = this.head; temp != null; temp = temp.next) {

            System.out.println(temp.FilePath);

        }

    }

}

