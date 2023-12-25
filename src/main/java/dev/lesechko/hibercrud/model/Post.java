package dev.lesechko.hibercrud.model;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToMany(fetch = FetchType.EAGER)    //TODO: EAGER, иначе в PostView стр.124 "...proxy - no Session". Не понимаю, как избежать?
    @JoinTable(                             // https://vladmihalcea.com/the-open-session-in-view-anti-pattern/
            name = "post_labels",           // https://vladmihalcea.com/the-best-way-to-handle-the-lazyinitializationexception/
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "label_id") }
    )
    private List<Label> labels;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    public Post() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}
