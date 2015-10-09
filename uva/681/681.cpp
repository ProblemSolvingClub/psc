#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> ii;

/*
 * convex hull
 */

int cross(ii& o, ii& a, ii& b) {
	return (a.second - o.second) * (b.first - o.first)
			- (a.first - o.first) * (b.second - o.second);
}

int main() {
	int K;
	cin >> K;
	cout<<K<<'\n';
	int k=K;
	while (k--) {
		if(k<K-1)cout<<"-1\n";
		int n,m=0;
		cin >> n;
		vector<ii> v(n),h(n*2);
		for (int i = 0; i < n; i++) {
			//  v[i].first is y, so we can compare y first
			cin >> v[i].second >> v[i].first;
		}
		int n1;cin>>n1;
		sort(v.begin(), v.end());
		for(int i=0;i<n;i++){
			while(m>=2&&cross(h[m-2],h[m-1],v[i])<=0)m--;
			h[m++]=v[i];
		}
		for(int i=n-2,t=m+1;i>=0;i--){
			while(m>=t&&cross(h[m-2],h[m-1],v[i])<=0)m--;
			h[m++]=v[i];
		}
		h.resize(m);
		cout<<m<<'\n';
		for(ii i:h){
			cout<<i.second<<' '<<i.first<<'\n';
		}
	}
}
