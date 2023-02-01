
# Steps

A brief description of what this project does and who it's for


## Usage/Examples

step 1 - Add dependencies to project:

```java harmony
ext {
    daggerVersion = '2.44.2'
    paging_version = '3.1.1'
    room_version = "2.2.5"
    lifecycle_version = "2.5.1"
}  

dependencies {
    ...

    //*** Retrofit library for database of movies ****************************
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    //*** Gson **********************************************************
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    // *** Glide Library **********************************************************
    implementation 'com.github.bumptech.glide:glide:4.13.2'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.13.2'

    // *** Room **********************************************************
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-rxjava2:$room_version"

    // *** RxJava3 **********************************************************
    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
    implementation 'io.reactivex.rxjava3:rxjava:3.0.2'
    implementation "com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0"
}

```

step 2 - Add ViewBinding:

```java harmony
    buildFeatures {
        viewBinding true
    }
```

Step 3 - Add internet permission to Manifest:
```java harmony
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

step 4 - Add keys to keymap :

```raw
Shift+F - blank Fragment
Shift+A - blank Activity
Shift+P - create new package
Shift+J - new java class
```

step 5 - Add drawables:

(5.1) bg_white_rounded:

```xml harmony
    <?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="#ffffff"/>
    <corners android:radius="10dp"/>
    <padding android:left="0dp"
        android:bottom="0dp"
        android:right="0dp"
        android:top="0dp"/>
</shape>
```


(5.2) ic_no_image:
```xml harmony
<vector android:height="24dp" android:tint="#000000"
    android:viewportHeight="24" android:viewportWidth="24"
    android:width="24dp" xmlns:android="http://schemas.android.com/apk/res/android">
    <path android:fillColor="@android:color/white" android:pathData="M21.9,21.9l-8.49,-8.49l0,0L3.59,3.59l0,0L2.1,2.1L0.69,3.51L3,5.83V19c0,1.1 0.9,2 2,2h13.17l2.31,2.31L21.9,21.9zM5,18l3.5,-4.5l2.5,3.01L12.17,15l3,3H5zM21,18.17L5.83,3H19c1.1,0 2,0.9 2,2V18.17z"/>
</vector>

```

step 6 -

(6.1) Create package **ui** -> **object_list**, **object_details**.

(6.2) Create *object_list* -> **activity** Then move the MainActivity to there and rename it to ObjectListActivity.

(6.3) Rename also the activity_main_layout.

(6.4) In activity_object_list:

```xml harmony
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".ui.contact_list.activity.ContactListActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            android:background="#EFECEC"
            android:orientation="vertical">

            <SearchView
                android:id="@+id/activityContactList_SV_searchView"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="20dp"
                android:background="@drawable/bg_white_rounded"
                android:drawablePadding="12dp"
                android:ems="10"
                android:hint="@string/search_contact"
                android:padding="5dp"
                android:textColor="@color/black"
                android:textColorHint="@color/black"
                android:textSize="16sp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="5dp"
                android:text="Filter By:"
                android:textSize="18sp" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="5dp"
                android:gravity="center|start"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Parameter:" />

                <RadioGroup
                    android:id="@+id/radiogroup_sortParameter"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="3"
                    android:orientation="horizontal">

                    <RadioButton
                        android:id="@+id/radio_all"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:text="All" />

                    <RadioButton
                        android:id="@+id/radio_id"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:text="Id" />

                    <RadioButton
                        android:id="@+id/radio_name"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:text="Name" />

                    <RadioButton
                        android:id="@+id/radio_Age"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:text="Age" />
                </RadioGroup>
            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="5dp"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="match_parent"
                    android:layout_weight="1"
                    android:gravity="center|start"
                    android:text="Asc/Desc:" />

                <RadioGroup
                    android:id="@+id/radiogroup_sort"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="3"
                    android:orientation="horizontal">

                    <RadioButton
                        android:id="@+id/radio_ascending"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:text="Ascending" />

                    <RadioButton
                        android:id="@+id/radio_descending"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:text="Desc" />
                </RadioGroup>

            </LinearLayout>


            <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/activityContactList_RV_recyclerView"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="5dp" />

            <ProgressBar
                android:id="@+id/activityContactList_PB_progressBar"
                style="?android:attr/progressBarStyle"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_margin="100dp" />
        </LinearLayout>


    </LinearLayout>
</LinearLayout>
```
 
 
step 7 - Create object_list_item:

```xml harmony
  <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <androidx.cardview.widget.CardView
        android:id="@+id/contactListItem_CV_contactItemCard"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_margin="10dp"
        android:clickable="true"
        android:focusable="true"
        app:cardBackgroundColor="@color/white"
        app:cardCornerRadius="10dp"
        app:cardElevation="10dp">

        <LinearLayout
            android:id="@+id/list_item_layout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <TextView
                android:id="@+id/contactListItem_TXT_name"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:fontFamily="sans-serif-black"
                android:text="@string/name"
                android:textColor="@color/black"
                android:textSize="25sp"
                android:textStyle="bold" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">
                <TextView
                    android:id="@+id/contactListItem_TXT_idPreview"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_margin="10dp"
                    android:fontFamily="sans-serif-black"
                    android:text="@string/id_preview"
                    android:textColor="@color/black"
                    android:textSize="20sp" />
                <TextView
                    android:id="@+id/contactListItem_TXT_id"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_margin="10dp"
                    android:fontFamily="sans-serif-black"
                    android:text="@string/contact_id"
                    android:textColor="@color/black"
                    android:textSize="20sp" />
            </LinearLayout>
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">
                <TextView
                    android:id="@+id/contactListItem_TXT_agePreview"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_margin="10dp"
                    android:fontFamily="sans-serif-black"
                    android:text="@string/age_preview"
                    android:textColor="@color/black"
                    android:textSize="20sp" />
                <TextView
                    android:id="@+id/contactListItem_TXT_age"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_margin="10dp"
                    android:fontFamily="sans-serif-black"
                    android:text="@string/age"
                    android:textColor="@color/black"
                    android:textSize="20sp" />
            </LinearLayout>

        </LinearLayout>
    </androidx.cardview.widget.CardView>
</LinearLayout>
```


step 8 - 

(8.1) Create *object_details* -> **activity** -> **ObjectDetailsActivity**.

(8.2) in activity_object_details:
```xml harmony
  <?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ui.contact_details.activity.ContactDetailsActivity">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <androidx.cardview.widget.CardView
                android:id="@+id/activityContactDetails_CV_recipeItemCard"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_margin="10dp"
                android:clickable="true"
                android:focusable="true"
                app:cardBackgroundColor="@color/white"
                app:cardCornerRadius="10dp"
                app:cardElevation="10dp">

                <LinearLayout
                    android:id="@+id/list_item_layout"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:orientation="vertical">

                    <TextView
                        android:id="@+id/activityContactDetails_TXT_name"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_margin="10dp"
                        android:fontFamily="sans-serif"
                        android:text="@string/name"
                        android:textColor="@color/black"
                        android:textSize="30sp"
                        android:textStyle="bold" />

                    <ImageView
                        android:id="@+id/activityContactDetails_IMG_contactImage"
                        android:layout_width="match_parent"
                        android:layout_height="150dp"
                        android:contentDescription="@string/image"
                        android:src="@drawable/ic_no_image" />
                    
                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:orientation="horizontal">

                        <TextView
                            android:id="@+id/activityContactDetails_TXT_idPreview"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_margin="10dp"
                            android:fontFamily="sans-serif"
                            android:text="@string/id_preview"
                            android:textColor="@color/black"
                            android:textSize="20sp" />
                        <TextView
                            android:id="@+id/activityContactDetails_TXT_id"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_margin="10dp"
                            android:fontFamily="sans-serif"
                            android:text="@string/contact_id"
                            android:textColor="@color/black"
                            android:textSize="20sp" />

                    </LinearLayout>

                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:orientation="horizontal">

                        <TextView
                            android:id="@+id/activityContactDetails_TXT_agePreview"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_margin="10dp"
                            android:fontFamily="sans-serif"
                            android:text="@string/age_preview"
                            android:textColor="@color/black"
                            android:textSize="20sp" />
                        <TextView
                            android:id="@+id/activityContactDetails_TXT_age"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_margin="10dp"
                            android:fontFamily="sans-serif"
                            android:text="@string/age"
                            android:textColor="@color/black"
                            android:textSize="20sp" />

                    </LinearLayout>

                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:orientation="horizontal">

                        <TextView
                            android:id="@+id/activityContactDetails_TXT_emailPreview"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_margin="10dp"
                            android:fontFamily="sans-serif"
                            android:text="@string/email_preview"
                            android:textColor="@color/black"
                            android:textSize="20sp" />
                        <TextView
                            android:id="@+id/activityContactDetails_TXT_email"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_margin="10dp"
                            android:fontFamily="sans-serif"
                            android:text="@string/email"
                            android:textColor="@color/black"
                            android:textSize="20sp" />

                    </LinearLayout>

                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:orientation="horizontal">

                        <TextView
                            android:id="@+id/activityContactDetails_TXT_phonePreview"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_margin="10dp"
                            android:fontFamily="sans-serif"
                            android:text="@string/phone_preview"
                            android:textColor="@color/black"
                            android:textSize="20sp" />
                        <TextView
                            android:id="@+id/activityContactDetails_TXT_phone"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_margin="10dp"
                            android:fontFamily="sans-serif"
                            android:text="@string/phone"
                            android:textColor="@color/black"
                            android:textSize="20sp" />

                    </LinearLayout>
                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:orientation="horizontal">

                        <TextView
                            android:id="@+id/activityContactDetails_TXT_ipPreview"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_margin="10dp"
                            android:fontFamily="sans-serif"
                            android:text="@string/ip_preview"
                            android:textColor="@color/black"
                            android:textSize="20sp" />
                        <TextView
                            android:id="@+id/activityContactDetails_TXT_ip"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_margin="10dp"
                            android:fontFamily="sans-serif"
                            android:text="@string/ip"
                            android:textColor="@color/black"
                            android:textSize="20sp" />

                    </LinearLayout>
                </LinearLayout>
            </androidx.cardview.widget.CardView>
        </LinearLayout>

    </ScrollView>

</androidx.constraintlayout.widget.ConstraintLayout>
```


Step 9 - Look at the JSON and build models in **data** -> **model**.
Be aware of the Types!!! 

<u>If the detailed object has same parameteres as the original object create a combined object with all parameters(8.1 + 8.2 in one class) </u>

(9.1)  **ObjectItem** with sorting parameters and <u>override equals</u>:


```java harmony

    public String getFullName() {
        return Stream.of(firstName, lastName)
                .filter(x -> x != null && !x.isEmpty())
                .collect(joining(" "));
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj!=null) {
            Contact contact=(Contact) obj;
            return id==contact.getId();
        }
        return false;
    }

    public static class SortByName implements Comparator<Contact> {
        // Used for sorting title
        public int compare(Contact c1, Contact c2) {
            return c1.getFirstName().compareTo(c2.getFirstName());
        }
    }
    public static class SortByAge implements Comparator<Contact> {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Contact c1, Contact c2) {
            return c1.getAge() - c2.getAge();
        }
    }
    public static class SortById implements Comparator<Contact> {
        // Used for sorting title
        public int compare(Contact c1, Contact c2) {
            return c1.getId()-(c2.getId());
        }
    }

```


(9.2) **ObjectDetails**


 Step 10 - Create **common** -> **Constants**:

```java harmony
    public static final String BASE_URL="https://dummyjson.com/";
    public static final String LOG="Log";
    public static final String SELECTED_ITEM =  "Selected Item";
    public static final String ITEM_DETAILS =  "Item Details";

```


Step 9 - Create *data* -> **remote** ->

(10.1) **JsonApiObject**:
```java harmony
public interface JsonApiContacts {
    @GET("users")
    Observable<ContactList> getAllContacts();

    @GET("users/{contactId}")
    Observable<Contact> getContactDetails(
            @Path("contactId") String id
    );
}

```

(10.2) **ApiService**:

```java harmony
public class ApiService {
    private static ApiService INSTANCE = null;
    private JsonApiContacts jsonApiContacts;
    private ApiService() {
        initializeRetrofit();
    }

    public static ApiService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ApiService();

        return INSTANCE;
    }

    private void initializeRetrofit() {
        jsonApiContacts =new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(JsonApiContacts.class);
    }

    public JsonApiContacts getJsonApiContacts() {
        return jsonApiContacts;
    }
}


```

Step 11 - Create **repository** -> **Repository**:

```java harmony
public class Repository {
    private static Repository INSTANCE = null;

    private JsonApiContacts jsonApiContacts;
    public static Repository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Repository();

        return INSTANCE;
    }
    private Repository( ) {
        this.jsonApiContacts = ApiService.getInstance().getJsonApiContacts();
    }

    public Observable<ContactList> getAllContacts(){
        return jsonApiContacts.getAllContacts();
    }
    public Observable<Contact> getContactDetails(String id){
        return jsonApiContacts.getContactDetails(id);
    }
}


```

Step 12 - Create **util** -> **ImageUtil**:

```java harmony
public class ImageUtil {
    public static void setImage(Context context, String imageUrl, ImageView imageView,int width,int height) {
        Glide.with(context)
                .load(imageUrl)
                .override(width, height)
                .into(imageView);
    }
}

```

Step 13 - Create *ui* -> **callback** ->

(13.1) 🟢**CustomItemClickListener**:

```java harmony
public interface CustomItemClickListener {
    void onClick(Object object);
}

```

(13.2) **ObjectDiffCallback**:

```java harmony
public class ContactDiffCallback extends DiffUtil.Callback {
    private final List<Contact> mOldContactList;
    private final List<Contact> mNewContactList;

    public ContactDiffCallback(List<Contact> mOldContactList, List<Contact> mNewContactList) {
        this.mOldContactList = mOldContactList;
        this.mNewContactList = mNewContactList;
    }

    @Override
    public int getOldListSize() {
        return mOldContactList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewContactList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldContactList.get(oldItemPosition).equals(mNewContactList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Contact oldContact = mOldContactList.get(oldItemPosition);
        final Contact newContact = mNewContactList.get(newItemPosition);

        return oldContact.equals(newContact);
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}


```

Step 14 - In *ui* -> *object_item_list* ->

(14.1) Create **FilterType**:

```java harmony
public enum FilterType {
    ALL,NAME, AGE, ID
}
```

(14.2) Create **SortType**:

```java harmony
public enum SortType {
    ASC,DESC
}

```


(14.2) Create **ObjectListEvent**:
```java harmony
public enum ContactListEvent {
    FILTER_LIST, ITEM_CLICKED, SEARCH
}


```

Step 15 - Create *ui* -> *object_item_list* -> **adapter** -> **ObjectAdapter**:

```java harmony
public class ContactListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ContactListItemBinding contactListItemBinding;
    private List<Contact> contactList;
    private CustomItemClickListener customItemClickListener;

    private Context context;

    public ContactListAdapter(Context context) {
        this.customItemClickListener=(CustomItemClickListener)context;
        this.contactList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        contactListItemBinding = ContactListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        setListeners(myViewHolder,contact);
        myViewHolder.contactListItemBinding.contactListItemTXTName.setText(contact.getFullName());
        myViewHolder.contactListItemBinding.contactListItemTXTId.setText(String.valueOf(contact.getId()));
        myViewHolder.contactListItemBinding.contactListItemTXTAge.setText(String.valueOf(contact.getAge()));
    }

    private void setListeners(MyViewHolder myViewHolder, Contact contact) {
        myViewHolder.contactListItemBinding.contactListItemCVContactItemCard.setOnClickListener(v -> {
            customItemClickListener.onClick(contact);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void updateRecipeListItems(List<Contact> contactList) {
        final ContactDiffCallback diffCallback = new ContactDiffCallback(this.contactList, contactList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.contactList.clear();
        this.contactList.addAll(contactList);
        this.notifyDataSetChanged();
        diffResult.dispatchUpdatesTo(this);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ContactListItemBinding contactListItemBinding;

        public MyViewHolder(ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;

        }
    }
}


```

Step 16 - Create *ui* -> *object_item_list* -> **ObjectListViewModel**:

```java harmony
public class ContactListViewModel extends ViewModel {
    private MutableLiveData<List<Contact>> contactListLiveData;
    private MutableLiveData<List<Contact>> filteredContactListLiveData;

    // Variable for hiding and showing the loading spinner
    private MutableLiveData<Boolean> loading;
    private MutableLiveData<String> currentSearchTextLiveData;
    private PublishSubject<ContactList> contactListSubject;
    private CompositeDisposable disposables;
    private Repository repository;
    private FilterType selectedFilter;
    private SortType selectedSort;


    public ContactListViewModel() {
        this.repository = Repository.getInstance();

        init();
        subscribeSubject();
    }

    private void init() {
        contactListLiveData = new MutableLiveData<>();
        filteredContactListLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        currentSearchTextLiveData = new MutableLiveData<>("");

        contactListSubject = PublishSubject.create();
        disposables = new CompositeDisposable();
        selectedFilter = FilterType.ALL;
        selectedSort=SortType.ASC;
    }

    private void subscribeSubject() {
        Disposable disposable =
                repository.getAllContacts()
                        .subscribeOn(Schedulers.io())
                        .subscribe(contactListSubject::onNext, throwable -> {
                            Log.e(Constants.LOG, "From SubscribeSubject error: " + throwable.getMessage());
                        });
        disposables.add(disposable);
    }

    public MutableLiveData<List<Contact>> getContactListLiveData() {
        return contactListLiveData;
    }

    public MutableLiveData<List<Contact>> getFilteredContactListLiveData() {
        return filteredContactListLiveData;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public MutableLiveData<String> getCurrentSearchTextLiveData() {
        return currentSearchTextLiveData;
    }

    public void getContacts() {
        contactListSubject
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(contacts -> {
                    List<Contact> contactList=new ArrayList<>();
                    for (Contact contact:contacts.getContactList()) {
                        contactList.add(contact);
                    }
                    return contactList;
                })
                // No need to map
                .subscribe(new Observer<List<Contact>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        loading.setValue(true);
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<Contact> contacts) {
                        loading.setValue(false);
                        contactListLiveData.setValue(contacts);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(Constants.LOG, "getContacts error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // Nothing to do here
                    }
                });
    }


    public void onEventMenuItemList(ContactListEvent event, Object object) {
        switch (event) {
            //TODO: FILTER

            case FILTER_LIST: {
                if(object instanceof FilterType)
                  selectedFilter = (FilterType) object;
                else if(object instanceof SortType)
                    selectedSort = (SortType)object;
                filterList();
                break;
            }
            case SEARCH: {
                String searchQuery=(String)object;
                searchMenuItems(searchQuery.toLowerCase());
                if(filteredContactListLiveData.getValue()!=null) {
                    if(filteredContactListLiveData.getValue().isEmpty())
                        loading.setValue(false);
                }
                break;
            }
        }
    }

    private void filterList() {
        List<Contact> filteredMenuItems;
        if (contactListLiveData.getValue() == null)
            return;
        else if (filteredContactListLiveData.getValue() == null)
            filteredMenuItems = contactListLiveData.getValue();
        else
            filteredMenuItems = filteredContactListLiveData.getValue();

        filterCases(filteredMenuItems);
    }
    private void filterCases(List<Contact> filteredContacts) {
        switch (selectedFilter) {
            case ALL:
                currentSearchTextLiveData.setValue("");
                filteredContacts =contactListLiveData.getValue();
                break;

            case AGE:
                if(selectedSort.equals(SortType.ASC))
                    Collections.sort(filteredContacts,  new Contact.SortByAge());
                else
                    Collections.sort(filteredContacts, new Contact.SortByAge().reversed());

                break;

            case ID:
                if(selectedSort.equals(SortType.ASC))
                    Collections.sort(filteredContacts, new Contact.SortById());
                else
                    Collections.sort(filteredContacts, new Contact.SortById().reversed());

                break;


            case NAME:
                if(selectedSort.equals(SortType.ASC))
                    Collections.sort(filteredContacts, new Contact.SortByName());
                else
                    Collections.sort(filteredContacts, new Contact.SortByName().reversed());

                break;

        }
        filteredContactListLiveData.setValue(filteredContacts);

    }
    public void disposeComposite() {
        disposables.dispose();
    }
// TODO: SEARCH 2

    public void searchMenuItems(String searchQuery) {
        currentSearchTextLiveData.setValue(searchQuery);
        List<Contact> filteredMenuItems = new ArrayList<>();
        if (contactListLiveData.getValue() != null) {
            for (Contact menuItem : contactListLiveData.getValue()) {
                if (menuItem.getFullName().toLowerCase().contains(searchQuery)) {
                    filteredMenuItems.add(menuItem);
                }
            }
            filteredContactListLiveData.setValue(filteredMenuItems);
        }
    }
}

```

Step 17 - In *ui* -> *object_item_list* -> *activity* -> *ObjectListActivity*:

```java harmony
public class ContactListActivity extends AppCompatActivity implements LifecycleOwner, CustomItemClickListener {
    private ContactListViewModel contactListViewModel;
    private ContactDetailsViewModel contactDetailsViewModel;
    private RecyclerView recyclerView;
    private ContactListAdapter contactListAdapter;
    private ProgressBar progressBar;

    private ActivityContactListBinding activityContactListBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        activityContactListBinding = ActivityContactListBinding.inflate(getLayoutInflater());
        View view = activityContactListBinding.getRoot();
        setContentView(view);

        setViewModels();
        setViews();
        setObservers();

        setListeners();
        setRecyclerView();
        setAdapter();
        setMenuItemListUI();
    }

    private void setViews() {
        progressBar = activityContactListBinding.activityContactListPBProgressBar;
        // Set the default sort direction to ascending
        activityContactListBinding.radioAscending.setChecked(true);
        activityContactListBinding.radioAll.setChecked(true);
    }

    private void setViewModels() {
        contactListViewModel = new ViewModelProvider(this).get(ContactListViewModel.class);
        contactDetailsViewModel = new ViewModelProvider(this).get(ContactDetailsViewModel.class);
    }

    private void setObservers() {
        contactListViewModel.getContactListLiveData().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactListAdapter.updateRecipeListItems(contacts);
            }
        });
        contactListViewModel.getFilteredContactListLiveData().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> filteredContacts) {
                contactListAdapter.updateRecipeListItems(filteredContacts);
            }
        });
        contactListViewModel.getCurrentSearchTextLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String searchText) {
                activityContactListBinding.activityContactListSVSearchView.setQuery(searchText, false);
            }
        });
        // TODO: CONTACT DETAILS OBSERVE
        contactDetailsViewModel.getContactDetailsLiveData().observe(this, new Observer<Contact>() {
            @Override
            public void onChanged(Contact contact) {
                startContactDetailsActivity();
            }
        });

        contactListViewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading)
                    progressBar.setVisibility(View.VISIBLE);
                else
                    progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setListeners() {
        setRadioButtonsListener();
        setRadioButtonsSortParameterListener();
        activityContactListBinding.activityContactListSVSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactListViewModel.onEventMenuItemList(ContactListEvent.SEARCH, newText);
                return true;
            }
        });

    }

    private void setRadioButtonsSortParameterListener() {
        activityContactListBinding.radiogroupSortParameter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
// Handle the change in the radio button state
                switch (checkedId) {
                    case R.id.radio_all:
                        // Sort the list of movies in ascending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, FilterType.ALL);

                        break;
                    case R.id.radio_id:
                        // Sort the list of movies in descending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, FilterType.ID);
                        break;
                    case R.id.radio_name:
                        // Sort the list of movies in descending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, FilterType.NAME);

                        break;
                    case R.id.radio_Age:
                        // Sort the list of movies in descending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, FilterType.AGE);
                        break;
                }
            }
        });
    }

    private void setRadioButtonsListener() {
        activityContactListBinding.radiogroupSort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
// Handle the change in the radio button state
                switch (checkedId) {
                    case R.id.radio_ascending:
                        // Sort the list of movies in ascending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, SortType.ASC);


                        break;
                    case R.id.radio_descending:
                        // Sort the list of movies in descending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, SortType.DESC);

                        break;
                }
            }
        });

    }


    private void startContactDetailsActivity() {
        String selectedItemJson = new Gson().toJson(contactDetailsViewModel.getSelectedItem().getValue());
        String itemDetailsJson = new Gson().toJson(contactDetailsViewModel.getContactDetailsLiveData().getValue());
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SELECTED_ITEM, selectedItemJson);
        bundle.putString(Constants.ITEM_DETAILS, itemDetailsJson);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setRecyclerView() {
        recyclerView = activityContactListBinding.activityContactListRVRecyclerView;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setAdapter() {
        contactListAdapter = new ContactListAdapter(this);
        recyclerView.setAdapter(contactListAdapter);
    }

    private void setMenuItemListUI() {
        contactListViewModel.getContacts();
    }

    @Override
    public void onClick(Object object) {
        contactDetailsViewModel
                .onEventRecipeList(
                        ContactDetailsEvent.GET_CONTACT_DETAILS,
                        object
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactListViewModel.disposeComposite();
    }
}

```

Step 18 - Create *ui* -> *object_details* -> **ObjectItemEvent**:

```java harmony
public enum ContactDetailsEvent {
    GET_CONTACT_DETAILS
}

```

Step 19 - Create *ui* -> *object_details* -> **ObjectItemViewModel**:

```java harmony
public class ContactDetailsViewModel extends ViewModel {
    private MutableLiveData<Contact> contactDetailsLiveData;
    private MutableLiveData<Contact> selectedItem;
    private Repository repository;
    private CompositeDisposable disposables;
    private PublishSubject<Contact> contactDetailsSubject;



    public ContactDetailsViewModel( ) {
        this.repository = Repository.getInstance();


        init();
    }

    private void init() {
        contactDetailsLiveData = new MutableLiveData<>();
        selectedItem = new MutableLiveData<>();
        disposables = new CompositeDisposable();

        contactDetailsSubject = PublishSubject.create();
    }

    public void subscribeSubject(String id) {
        Disposable disposable =
                repository.getContactDetails(id)
                        .subscribeOn(Schedulers.io())
                        .subscribe(contactDetailsSubject::onNext, throwable -> {
                            Log.e(Constants.LOG, "subscribeSubject error: " + throwable.getMessage());
                        });
        disposables.add(disposable);

    }

    public MutableLiveData<Contact> getContactDetailsLiveData() {
        return contactDetailsLiveData;
    }

    public MutableLiveData<Contact> getSelectedItem() {
        return selectedItem;
    }

    public void getMenuItemDetails() {
        contactDetailsSubject
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Contact>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Contact contactDetails) {
                        contactDetailsLiveData.setValue(contactDetails);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(Constants.LOG, "getContactDetails error: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        // Nothing to do here

                    }
                });

    }

    public void onEventRecipeList(ContactDetailsEvent contactDetailsEvent, Object object) {
        switch (contactDetailsEvent) {
            case GET_CONTACT_DETAILS:
                Contact contact = (Contact) object;
                selectedItem.setValue(contact);
                subscribeSubject(String.valueOf(contact.getId()));
                getMenuItemDetails();
                break;

        }

    }
}

```

Step 20 - Create *ui* -> *object_details* -> *activity* -> **ObjectDetailsActivity**:

```java harmony
public class ContactDetailsActivity extends AppCompatActivity {
    
    private ActivityContactDetailsBinding activityContactDetailsBinding;
    
    private static final int HEIGHT =1000 ;
    private static final int WIDTH =1000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        
        activityContactDetailsBinding = ActivityContactDetailsBinding.inflate(getLayoutInflater());
        View view=activityContactDetailsBinding.getRoot();
        setContentView(view);
        
        setUI();
    }

    private void setUI() {
        Contact contact=getItemData();
        Contact menuItemDetails = getItemDetails();
        setMenuItemUI(contact);
        setMenuItemDetailsUI(menuItemDetails);
    }
    private Contact getItemData() {
        Bundle bundle = getIntent ().getExtras ();
        String selectedItemJson = bundle.getString (Constants.SELECTED_ITEM);
        Contact selectedItem = new Gson().fromJson (selectedItemJson, Contact.class);
        return  selectedItem;
    }

    private Contact getItemDetails() {
        Bundle bundle = getIntent ().getExtras ();
        String itemDetailsJson = bundle.getString (Constants.ITEM_DETAILS);
        Contact menuItemDetails = new Gson().fromJson (itemDetailsJson, Contact.class);
        return menuItemDetails;
    }

    private void setMenuItemUI(Contact selectedItem) {
        activityContactDetailsBinding.activityContactDetailsTXTId.setText(String.valueOf(selectedItem.getId()));
        activityContactDetailsBinding.activityContactDetailsTXTAge.setText(String.valueOf(selectedItem.getAge()));
        activityContactDetailsBinding.activityContactDetailsTXTName.setText(selectedItem.getFullName());
    }

    private void setMenuItemDetailsUI(Contact menuItemDetails) {
        activityContactDetailsBinding.activityContactDetailsTXTEmail.setText(menuItemDetails.getEmail());
        activityContactDetailsBinding.activityContactDetailsTXTPhone.setText(menuItemDetails.getPhone());
        activityContactDetailsBinding.activityContactDetailsTXTIp.setText(menuItemDetails.getIp());
        setImageUI(menuItemDetails.getImage());
    }

    private void setImageUI(String imageUrl) {
        ImageUtil.setImage(this,imageUrl,activityContactDetailsBinding.activityContactDetailsIMGContactImage,WIDTH,HEIGHT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
```

Step 21 - 

Implement searching and filtering.




