package sample;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.web.UserIndoRepository;
import sample.web.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ForSpringTestApplication.class)

//リポジトリ用ユニットクラス
public class UserInfoRepositoryTest {
	@Autowired
	private UserIndoRepository testUserInfoRepo;

	@Before
	public void 事前処理() throws Exception{
		//ここで一回DBに登録している
		UserInfo Tuser = new UserInfo();
		Tuser.setFirstName("アナキン");
		Tuser.setLastName("スカイウォーカー");
		Tuser.setAge("42");

		testUserInfoRepo.save(Tuser);
	}

/*	@Test
	public void リポジトリーの確認2(){
		UserInfo T = testUserInfoRepo.findOne(1);
		String actual3 = "40";
		assertEquals(actual3,T.getAge());
	}*/


	//DBの一列目だけとってこれるテストケース
	@Test
	public void リポジトリーの確認(){

		//pageableを用いてidの最後尾一つを抽出
		Pageable pageable = new PageRequest(0, 1, Direction.DESC,"id");
		Page<UserInfo> T =testUserInfoRepo.findAll(pageable);

		//Page<UserInfo> Tが空でないことを確認
		assertNotNull(T);
		//Page<UserInfo> Tのサイズ（UserInfo）の抽出が1だけで確認
		assertTrue(T.getSize() == 1);

		//Page<UserInfo>→List<UserInfo>
		List<UserInfo> list = T.getContent();
		//Listからクラス「UserInfo」型に変換
		UserInfo UInfo = list.get(0);
		String actual1 = "アナキン";
		String actual2 = "スカイウォーカー";
		String actual3 = "43";
		assertEquals(actual3,UInfo.getAge());
		assertEquals(actual1,UInfo.getFirstName());
		assertEquals(actual2,UInfo.getLastName());
	}

	@Rule
    public ExpectedException exception = ExpectedException.none();

	//例外処理の場合のテスト
	@Test(expected = org.springframework.orm.jpa.JpaSystemException.class)
	public void 例外処理の確認(){
		//Page<UserInfo> Tが空でないことを確認
		//pageableを用いてidの最後尾一つを抽出
		Pageable pageable = new PageRequest(0, 1, Direction.DESC,"id");
		Page<UserInfo> T =testUserInfoRepo.findAll(pageable);

		assertNotNull(T);
		//Page<UserInfo> Tのサイズ（UserInfo）の抽出が1だけで確認
		assertTrue(T.getSize() == 1);

		//Page<UserInfo>→List<UserInfo>
		List<UserInfo> list = T.getContent();
		//Listからクラス「UserInfo」型に変換
		UserInfo UInfo = list.get(0);

		UInfo.setAge("aaa");
		testUserInfoRepo.save(UInfo);

		//exception.expect(org.springframework.orm.jpa.JpaSystemException.class);
	}


}
