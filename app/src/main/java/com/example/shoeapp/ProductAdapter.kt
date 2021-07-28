//    package com.example.shoeapp
//
//    import android.app.AlertDialog
//    import android.content.Context
//    import android.view.LayoutInflater
//    import android.view.ViewGroup
//    import androidx.recyclerview.widget.RecyclerView
//    import com.bumptech.glide.Glide
//    import kotlinx.android.synthetic.main.custom_item.view.*
//
//    class ProductAdapter (private val context: Context,private val  productModel: List<ProductModel)
//        :RecyclerView.Adapter<ProductAdapter.ProductViewHolder> (){
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {
//          val itemView =LayoutInflater.from(parent.context)
//              .inflate(R.layout.custom_item,parent,false)
//            return ProductViewHolder(itemView)
//        }
//
//        override fun getItemCount() = ProductModel.size{
//            class ProductViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {
//                //ref views by id
//                var productImage = itemView.imageView2
//                var productNAME = itemView.textView7
//                var productName = itemView.textView8
//                var productPrice = itemView.textView9
//                var itemClick = itemView.linear
//            }
//        }
//
//        override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
//          //current position of each item
//            val currentShoe = productModel[position]
//            holder.productName.text =currentShoe.shoeName
//            holder.productPrice.text = currentShoe.shoePrice
//            holder.productPhone.text =currentShoe.ShoePhone
//                Glide.with(context)
//                .load(currentShoe.shoeImage)
//                .into(holder.productImage)
//            //variable to stor ethe item
//            val id = currentShoe.id
//            val price =currentShoe.shoePrice
//            val phone = currentShoe.ShoePhone
//            val prodname = currentShoe.shoeName
//            val imagepath = currentShoe.shoeImage
//            val contactname = currentShoe.Shoecontuct
//            holder.itemClick.setOnclickListener{
//                updateandDeleteDialog(id,price,phone,prodname,imagepath,contactname)
//            }
//        }
//
//        private fun updateandDeleteDialog(id: String, price: String, phone: String, prodname: String, imagepath: String, contactname: String): Any {
//         val dialgBuilder =AlertDialog.Builder(context)
//            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
//            as LayoutInflater
//            //attacching the dialog interface
//            val dialogView =inflater.inflate(R.layout.updatedeletedialoge, null)
//             dialogBuilder.setView(dialogView)
//            // view identification
//        }
//    }
//
//
//
//
//
//
