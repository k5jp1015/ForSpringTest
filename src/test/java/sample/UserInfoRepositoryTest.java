package sample;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
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

		//Page<UserInfo>→List<UserInfo>
		List<UserInfo> list = T.getContent();
		//Listからクラス「UserInfo」型に変換
		UserInfo UInfo = list.get(0);
		String actual1 = "アナキン";
		String actual2 = "スカイウォーカー";
		String actual3 = "42";
		assertEquals(actual3,UInfo.getAge());
		assertEquals(actual1,UInfo.getFirstName());
		assertEquals(actual2,UInfo.getLastName());
	}


}
