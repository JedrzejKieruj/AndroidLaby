package com.punky.lab4.tasks;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.random;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TaskListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Task> ITEMS = new ArrayList<Task>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

    public static void addItem(Task item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.contactName, item);
    }

     /* private static Task createDummyItem(int position) {
        return new Task(String.valueOf(position), "Item " + position, makeDetails(position));
    } */

    private static String makeDetails(int position, TaskListContent.Task task) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore contactBirthday information here.");
        }
        return task.contactRingtone;
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Task implements Parcelable {
        public final String contactName;
        public final String contactSurname;
        public final String contactBirthday;
        public final String contactPhone;
        public final String contactRingtone;
        public final String picPath;

        public Task(String contactName, String contactSurname, String contactPhone, String contactBirthday, String contactRingtone, String picPath) {
            this.contactName = contactName;
            this.contactSurname = contactSurname;
            this.contactBirthday = contactBirthday;
            this.contactPhone = contactPhone;
            this.contactRingtone = contactRingtone;
            this.picPath = picPath;

        }


        protected Task(Parcel in) {
            contactName = in.readString();
            contactSurname = in.readString();
            contactBirthday = in.readString();
            contactPhone = in.readString();
            contactRingtone = in.readString();
            picPath = "drawable" + (int)(  1 + random() * 5 );
        }

        public static final Creator<Task> CREATOR = new Creator<Task>() {
            @Override
            public Task createFromParcel(Parcel in) {
                return new Task(in);
            }

            @Override
            public Task[] newArray(int size) {
                return new Task[size];
            }
        };


        @Override
        public String toString() {
            return contactRingtone;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(contactName);
            parcel.writeString(contactSurname);
            parcel.writeString(contactBirthday);
            parcel.writeString(contactPhone);
            parcel.writeString(contactRingtone);
            parcel.writeString(picPath);
        }
    }
    public static void removeItem(int position) {
        String itemId = ITEMS.get(position).contactName;
        ITEMS.remove(position);
        ITEM_MAP.remove(itemId);
    }
}
