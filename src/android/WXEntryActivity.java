package your.package.wxapi;

import org.apache.cordova.CordovaActivity;
import com.qq.cordova.wechat.Wechat;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXEntryActivity extends CordovaActivity implements IWXAPIEventHandler {

	private IWXAPI api;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
		// 通过WXAPIFactory工厂，获取IWXAPI的实例
		api = WXAPIFactory.createWXAPI(this, "wx8b82e9ae3c45b7f2", false);
		api.registerApp("wx8b82e9ae3c45b7f2");
		api.handleIntent(getIntent(), this);
	}
	
	@Override
	public void onReq(BaseReq req) {
		
	}

	@Override
	public void onResp(BaseResp resp) {
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			Wechat.currentCallbackContext.success("分享成功");
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			Wechat.currentCallbackContext.success("发送取消");
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			Wechat.currentCallbackContext.success("发送被拒绝");
			break;
		default:
			Wechat.currentCallbackContext.success("发送返回");
			break;
		}
		finish();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		setIntent(intent);
        api.handleIntent(intent, this);
	}
}
