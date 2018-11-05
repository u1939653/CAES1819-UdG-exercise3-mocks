package org.udg.caes.account;

import mockit.Expectations;
import mockit.Verifications;
import org.junit.jupiter.api.Test;

import mockit.Tested;
import mockit.Mocked;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountService {

  @Test
  void testTransfer(@Tested AccountService as, @Mocked AccountManager am)
  {
    Account l_acc1 = new Account("acc1",10);
    Account l_acc2 = new Account("acc2",5);

    new Expectations()
    {{
      am.findAccount("acc1"); result = l_acc1;
      am.findAccount("acc2"); result = l_acc2;
    }};

    as.setAccountManager(am);
    as.transfer("acc2","acc1",5);

    assertEquals(l_acc1.getBalance(),15);
    assertEquals(l_acc2.getBalance(),0);

    new Verifications()
    {{
      am.updateAccount(l_acc1); times = 1;
      am.updateAccount(l_acc2); times = 1;
    }};

  }
}