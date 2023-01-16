package com.freeefly;

import com.rp.courseutil.Util;
import com.rp.sec11.assignment.SlackMember;
import com.rp.sec11.assignment.SlackRoom;

public class Sec13Assign {

    public static void main(String[] args) {
        SlackRoom slackRoom = new SlackRoom("slack-room");
        SlackMember freeefly = new SlackMember("freeefly");
        SlackMember jake = new SlackMember("jake");
        SlackMember mike = new SlackMember("mike");

        slackRoom.joinRoom(freeefly);
        slackRoom.joinRoom(jake);
        Util.sleepMillis(100);

        freeefly.says("Hi, all");
        jake.says("Hey!");

        slackRoom.joinRoom(mike);
        mike.says("I simply wanted to say something");

        Util.sleepSeconds(5);

    }

}
