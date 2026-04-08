package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.entity.base.BaseUpdatableEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Channel extends BaseUpdatableEntity {

  // 어디 그룹에 속한 채널인가
  private String group; // 채널 그룹
  private String name; // 채널 이름

  // 코드 탬플릿에 맞게 필드 추가
  private Type type;
  private String description;
  private List<UUID> participantIds;

  // 생성자
  public Channel(String group, String name, String description) {
    this.group = group;
    this.name = name;
    this.description = description;
  }

  // 정적 팩토리 메서드
  // 코드 탬플릿에 적합한 생성자 오버로딩
  private Channel(Type type, String name, String description) {
    this.type = type;
    this.name = name;
    this.description = description;
    this.participantIds = new ArrayList<>();
  }

  //    // 정적 팩토리 메서드(Private 채널 생성)
//    public static Channel create(Type channelType, String name, String description) {
//        return new Channel(channelType, name, description);
//    }
  // 정적 팩토리 메서드(Private 채널 생성)
  public static Channel createPrivate() {
    return new Channel(Type.PRIVATE, null, null);
  }

  // 정적 팩토리 메서드(Public 채널 생성)
  public static Channel createPublic(String name, String description) {
    return new Channel(Type.PUBLIC, name, description);
  }

  // getter(Lombok의 @Getter로 대체)

  // update
  public void updateGroup(String group) {
    this.group = group;
  }

  public void updateName(String name) {
    this.name = name;
  }

  public void updateDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "채널 이름 : " + name + ", 채널이 속해있는 그룹 : " + group;
  }

  public enum Type {
    PUBLIC, PRIVATE;
  }
}
