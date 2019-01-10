package network.maverick.messaging_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Random;

public class MessageListActivity extends AppCompatActivity {

    private EditText editText;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private MemberData memberData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        editText = (EditText) findViewById(R.id.editText);
        messagesView = (ListView) findViewById(R.id.messages_view);

        messageAdapter = new MessageAdapter(this);
        messagesView.setAdapter(messageAdapter);

        memberData = new MemberData("Name", getRandomColor());
    }

    public void sendMessage(View view) {
        try {
            String messageString = editText.getText().toString();
            if (messageString.length() > 0) {
                Message message = new Message(messageString, memberData, new Random().nextBoolean());
                messageAdapter.add(message);
                messagesView.setSelection(messagesView.getCount() - 1);

                editText.getText().clear();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private String getRandomColor() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer("#");
        while(sb.length() < 7){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }
}