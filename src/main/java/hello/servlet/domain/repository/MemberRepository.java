package hello.servlet.domain.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hello.servlet.domain.member.Member;

/**
 * 이 클래스는 Member 객체를 관리하는 간단한 저장소입니다.
 * 주의: 이 구현은 동시성 문제를 처리하지 않습니다.
 * 실제 환경에서는 ConcurrentHashMap과 AtomicLong을 사용하여
 * 스레드 안전한 작업을 고려하세요.
 */
public class MemberRepository {

  private static Map<Long, Member> store = new HashMap<>();  // 회원을 ID를 키로 저장하는 정적 Map입니다. 
  private static long sequence = 0L; // 각 회원에게 고유한 ID를 생성하기 위한 정적 변수입니다. 
  
  private static final MemberRepository instance = new MemberRepository(); // Singleton 패턴을 구현하기 위한 정적 final 인스턴스입니다.
 
  // MemberRepository의 단일 인스턴스에 접근하기 위한 공용 정적 메서드입니다.
  public static MemberRepository getInstance() {
    return instance;
  }

  // 외부에서 인스턴스를 생성하지 못하도록 하는 private 생성자입니다.
  // 이렇게 하면 단일 인스턴스만 생성됩니다 (싱글톤 패턴).
  private MemberRepository() {
  }

  // 회원을 저장소에 저장합니다.
  //저장하기 전에 회원에게 고유한 ID를 할당합니다.
  public Member save(Member member) {
    member.setId(++sequence); // 시퀀스를 증가시키고 회원 ID를 설정합니다.
    store.put(member.getId(), member); // 맵에 회원을 저장합니다.
    return member; // 저장된 회원 객체를 반환합니다.
  }

  // ID로 회원을 찾습니다.
  public Member findById(Long id) {
    return store.get(id); // ID를 사용하여 맵에서 회원을 검색합니다.
  }

  // 저장소에 있는 모든 회원의 목록을 반환합니다.
  public List<Member> findAll() {
    return new ArrayList<>(store.values()); // 모든 회원을 포함하는 새 리스트를 생성합니다.
  }

  // 저장소에서 모든 회원을 삭제합니다.
  public void clearStore() {
    store.clear(); // 맵에서 모든 항목을 제거합니다.
  }
}