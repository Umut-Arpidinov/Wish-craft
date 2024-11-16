package com.google.wishcraft.presentation.ui.addWish

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentAddWishBinding
import com.google.wishcraft.domain.models.Wish
import com.google.wishcraft.presentation.extensions.simpleDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class AddWishFragment:
    BaseFragment<AddWishViewModel, FragmentAddWishBinding>(FragmentAddWishBinding::inflate) {

    override val viewModel: AddWishViewModel by viewModel()

    private var selectedPhotoUri: Uri? = null


    private var selectImageLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            selectedPhotoUri = uri
            sendImage()
        }


    override fun initialize() {
        super.initialize()
    }

    override fun initClicks()= with(binding){
        super.initClicks()
        ivAddImage.setOnClickListener {
            selectImageLauncher.launch("image/*")
        }
        btnSave.setOnClickListener {
            createNewWish()
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }

    override fun onError(message: String) {
        super.onError(message)
        simpleDialog(message)
    }

    override fun onLoading(loading: Boolean) = with(binding){
        super.onLoading(loading)
        progress.isVisible = loading
        btnSave.isEnabled = !loading
    }

    private fun sendImage() {
        val uri = selectedPhotoUri ?: return
        val contentResolver = activity?.contentResolver ?: return
        val cacheDir = activity?.cacheDir ?: return
        val parcelFileDescriptor =
            contentResolver.openFileDescriptor(uri, "r", null) ?: return
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(uri))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        viewModel.sendImageUri(file)
        binding.ivWishImage.setImageURI(uri)
        parcelFileDescriptor.close()
    }

    private fun ContentResolver.getFileName(fileUri: Uri): String {
        var name = ""
        val returnCursor = this.query(fileUri, null, null, null, null)
        if (returnCursor != null) {
            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            name = returnCursor.getString(nameIndex)
            returnCursor.close()
        }
        return name
    }

    private fun createNewWish() = with(binding){
        val staticObjectId = viewModel.selectedImageId
        val wishName = etWishName.text.toString()
        val wishPrice = etPrice.text.toString()
        val wishLink = etLink.text.toString()
        val wishDescription = etDescription.text.toString()
        val wish = Wish(
            staticObjectId = staticObjectId,
            link = wishLink,
            description = wishDescription,
            giftName = wishName,
            price = wishPrice.toInt(),
            desireRate = 0,
        )
        viewModel.createNewWish(wish)
    }


}