package domain;

public class Works {
  private Long id;
  private String title;
  private String description;
  private String fenlei;
  private String author;
  private String time;
  private Long num_zan;

  public Works(Long id, String title, String description, String fenlei, String author, String time, Long num_zan) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.fenlei = fenlei;
    this.author = author;
    this.time = time;
    this.num_zan = num_zan;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFenlei() {
    return fenlei;
  }

  public void setFenlei(String fenlei) {
    this.fenlei = fenlei;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Long getNum_zan() {
    return num_zan;
  }

  public void setNum_zan(Long num_zan) {
    this.num_zan = num_zan;
  }
}
