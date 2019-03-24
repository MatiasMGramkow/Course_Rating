package com.matias.ma1;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseModel implements Parcelable
{
    private String name;
    private int grade;

    protected CourseModel(Parcel in)
    {
        name = in.readString();
        grade = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeInt(grade);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<CourseModel> CREATOR = new Creator<CourseModel>()
    {
        @Override
        public CourseModel createFromParcel(Parcel in)
        {
            return new CourseModel(in);
        }

        @Override
        public CourseModel[] newArray(int size)
        {
            return new CourseModel[size];
        }
    };

    public CourseModel(String name, int grade)
    {
        this.name = name;
        this.grade = grade;
    }

    public CourseModel(String name)
    {
        this.name = name;
    }

    public CourseModel()
    {

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getGrade()
    {
        return grade;
    }

    public void setGrade(int grade)
    {
        this.grade = grade;
    }

    @Override
    public String toString()
    {
        return "CourseModel{" + "name='" + name + '\'' + ", grade=" + grade + '}';
    }
}
