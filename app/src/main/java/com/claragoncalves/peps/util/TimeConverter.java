package com.claragoncalves.peps.util;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class TimeConverter {

        @TypeConverter
        public Date longToDate(Long value){
            return new Date(value);
        }

        @TypeConverter
        public Long dateToLong(Date date){
            return date.getTime();
        }

}
