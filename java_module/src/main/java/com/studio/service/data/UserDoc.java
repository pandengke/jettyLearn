package com.studio.service.data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/3/18.
 */
@Entity
@Table(name = "d_user_doc")
public class UserDoc {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "org.hibernate.id.IncrementGenerator")
    private int id;
    @Column
    private String uid;
    @Column
    private String directory;
}
